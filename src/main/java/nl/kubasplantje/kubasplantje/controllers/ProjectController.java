package nl.kubasplantje.kubasplantje.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.kubasplantje.kubasplantje.dtos.ProjectDto;
import nl.kubasplantje.kubasplantje.models.ProjectModel;
import nl.kubasplantje.kubasplantje.services.ProjectService;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    @Autowired
    private ModelMapper modelMapper;
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectModel> projectModels = this.projectService.getProjects();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (ProjectModel projectModel : projectModels) {
                projectDtos.add(convertModelToDto(projectModel));
        }
        return ResponseEntity.ok(projectDtos);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> addNewProject(@RequestBody ProjectDto projectDto) {
        ProjectModel projectModel = convertDtoToModel(projectDto);
        ProjectDto addedProject = convertModelToDto(this.projectService.addProject(projectModel));
        return ResponseEntity.ok(addedProject);
    }

    @PatchMapping
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        ProjectModel projectModel = convertDtoToModel(projectDto);
        ProjectDto addedProject = convertModelToDto(this.projectService.updateProject(projectModel));
        return ResponseEntity.ok(addedProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Long> deleteProject(@PathVariable("projectId") Long projectId) {
        this.projectService.deleteProject(projectId);
        return ResponseEntity.ok(projectId);
    }

    private ProjectModel convertDtoToModel(ProjectDto projectDto) {
        return this.modelMapper.map(projectDto, ProjectModel.class);
    }

    private ProjectDto convertModelToDto(ProjectModel projectModel) {
        return this.modelMapper.map(projectModel, ProjectDto.class);
    }
}
