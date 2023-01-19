package nl.kubasplantje.kubasplantje.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.kubasplantje.kubasplantje.exceptions.ProjectsDependentOnTechException;
import nl.kubasplantje.kubasplantje.models.TechModel;
import nl.kubasplantje.kubasplantje.repositories.TechRepository;

@Service
public class TechService {
    private TechRepository techRepository;

    @Autowired
    public TechService (TechRepository techRepository) {
        this.techRepository = techRepository;
    }

    public List<TechModel> getTechs() {
        return this.techRepository.findAll();
    }

    public TechModel addTech(TechModel techModel) {
        return this.techRepository.save(techModel);
    }

    public TechModel updateTech(TechModel techModel) {
        TechModel tech = this.techRepository.findById(techModel.getId()).orElseThrow();
        tech.setTechName(techModel.getTechName());
        tech.setSkillRating(techModel.getSkillRating());
        tech.setCategory(tech.getCategory());
        return this.techRepository.save(tech);
    }

    public Long deleteTech(Long techId) {
        TechModel tech = this.techRepository.findById(techId).orElseThrow();
        if (tech.getUsedInProjects().isEmpty()) {
            this.techRepository.deleteById(techId);
        } else {
            throw new ProjectsDependentOnTechException(
                String.format("The projects with the id's: %s are dependant on this tech",
                tech.getUsedInProjects().stream().map(p -> p.getId().toString() + ": " + p.getProjectName()).collect(Collectors.toList()).toString())
            );
        }
        return techId;
    }
}
