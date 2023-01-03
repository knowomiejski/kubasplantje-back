package nl.kubasplantje.kubasplantje.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProjectDto {

    private Long id;
    private String projectName;
    private String description;
    private String client;
    private String link;
    private ArrayList<Long> usedTechIds;
    private Set<TechDto> usedTechs = new HashSet<>();

    public ProjectDto() {}

    // for GET with link
    public ProjectDto(Long id, String projectName, String description, String client, String link,
            Set<TechDto> usedTechs) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.client = client;
        this.link = link;
        this.usedTechs = usedTechs;
    }

    // for POST with link (has usedTechIds instead of TechDto's)
    public ProjectDto(String projectName, String description, String client, String link, ArrayList<Long> usedTechIds) {
        this.projectName = projectName;
        this.description = description;
        this.client = client;
        this.link = link;
        this.usedTechIds = usedTechIds;
    }

    // for GET without link
    public ProjectDto(Long id, String projectName, String description, String client, Set<TechDto> usedTechs) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.client = client;
        this.usedTechs = usedTechs;
    }

    // for POST without link (has usedTechIds instead of TechDto's)
    public ProjectDto(String projectName, String description, String client, ArrayList<Long> usedTechIds) {
        this.projectName = projectName;
        this.description = description;
        this.client = client;
        this.usedTechIds = usedTechIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<TechDto> getUsedTechs() {
        return usedTechs;
    }

    public void setUsedTechs(Set<TechDto> usedTechs) {
        this.usedTechs = usedTechs;
    }

    public ArrayList<Long> getUsedTechIds() {
        return usedTechIds;
    }

    public void setUsedTechIds(ArrayList<Long> usedTechIds) {
        this.usedTechIds = usedTechIds;
    }
}
