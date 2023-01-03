package nl.kubasplantje.kubasplantje.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addTech(TechModel techModel) {
        this.techRepository.save(techModel);
    }
}
