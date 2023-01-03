package nl.kubasplantje.kubasplantje.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity(name = "Project")
@Table(name = "projects")
public class ProjectModel {

    @Id
    @SequenceGenerator(name="project_id_seq_gen",
                       sequenceName="project_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="project_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String client;

    @Column(nullable = true)
    private String link;

    @ManyToMany
    @JoinTable(
        name="used_techs",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "tech_id")
    )
    private Set<TechModel> usedTechs = new HashSet<>();

    @Transient
    private ArrayList<Long> usedTechIds;

    public ProjectModel() {}

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

    public Set<TechModel> getUsedTechs() {
        return usedTechs;
    }

    public void setUsedTechs(Set<TechModel> usedTechs) {
        this.usedTechs = usedTechs;
    }

    public ArrayList<Long> getUsedTechIds() {
        return usedTechIds;
    }

    public void setUsedTechIds(ArrayList<Long> usedTechIds) {
        this.usedTechIds = usedTechIds;
    }

}
