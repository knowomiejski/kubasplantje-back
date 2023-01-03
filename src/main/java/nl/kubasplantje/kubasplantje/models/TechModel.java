package nl.kubasplantje.kubasplantje.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import nl.kubasplantje.kubasplantje.enums.TechCategories;

@Entity(name = "Tech")
@Table(name = "techs")
public class TechModel {

    @Id
    @SequenceGenerator(
        name = "tech_id_seq_gen",
        sequenceName = "tech_id_seq",
        allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tech_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String techName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TechCategories category;

    @Column(nullable = false)
    private float skillRating;

    @ManyToMany(mappedBy = "usedTechs")
    private Set<ProjectModel> usedInProjects = new HashSet<>();

    public TechModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public TechCategories getCategory() {
        return category;
    }

    public void setCategory(TechCategories category) {
        this.category = category;
    }

    public float getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(float skillRating) {
        this.skillRating = skillRating;
    }

    public Set<ProjectModel> getUsedInProjects() {
        return usedInProjects;
    }

    public void setUsedInProjects(Set<ProjectModel> usedInProjects) {
        this.usedInProjects = usedInProjects;
    }
}
