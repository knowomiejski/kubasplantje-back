package nl.kubasplantje.kubasplantje.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.kubasplantje.kubasplantje.exceptions.TechAlreadyPresentException;
import nl.kubasplantje.kubasplantje.exceptions.TechNotFoundException;
import nl.kubasplantje.kubasplantje.models.ProjectModel;
import nl.kubasplantje.kubasplantje.models.TechModel;
import nl.kubasplantje.kubasplantje.repositories.ProjectRepository;
import nl.kubasplantje.kubasplantje.repositories.TechRepository;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private TechRepository techRepository;

    @Autowired
    public ProjectService (ProjectRepository projectRepository, TechRepository techRepository) {
        this.projectRepository = projectRepository;
        this.techRepository = techRepository;
    }

    public List<ProjectModel> getProjects() {
        return this.projectRepository.findAll();
    }

    public ProjectModel addProject(ProjectModel projectModel) {
        for (Long techModelId : projectModel.getUsedTechIds()) {
            Optional<TechModel> techModel = this.techRepository.findById(techModelId);
            if (techModel.isPresent()) {
                projectModel = this.addTechToProject(projectModel, techModel.get());
            } else {
                throw new TechNotFoundException(String.format("The tech with the id: %s was not found", techModelId.toString()));
            }
        }
        return this.projectRepository.save(projectModel);
    }

    public ProjectModel updateProject(ProjectModel projectModel) {
        ProjectModel project = this.projectRepository.findById(projectModel.getId()).orElseThrow();

        for (Long techModelId : projectModel.getUsedTechIds()) {
            Optional<TechModel> techModel = this.techRepository.findById(techModelId);
            if (techModel.isPresent()) {
                projectModel = this.addTechToProject(projectModel, techModel.get());
            } else {
                throw new TechNotFoundException(String.format("The tech with the id: %s was not found", techModelId.toString()));
            }
        }

        project.setClient(projectModel.getClient());
        project.setDescription(projectModel.getDescription());
        project.setLink(projectModel.getLink());
        project.setProjectName(projectModel.getProjectName());
        project.setUsedTechs(projectModel.getUsedTechs());

        return this.projectRepository.save(project);
    }

    public Long deleteProject(Long projectId) {
        this.projectRepository.deleteById(projectId);
        return projectId;
    }

    private ProjectModel addTechToProject(ProjectModel projectModel, TechModel techModel) {
        if (!projectModel.getUsedTechs().contains(techModel)) {
            projectModel.getUsedTechs().add(techModel);
            return projectModel;
        } else {
            throw new TechAlreadyPresentException(String.format("The tech with the id: %s is already added to the project", techModel.getId().toString()));
        }
    }
}
