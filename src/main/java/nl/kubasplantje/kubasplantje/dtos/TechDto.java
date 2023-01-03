package nl.kubasplantje.kubasplantje.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.kubasplantje.kubasplantje.enums.TechCategories;

public class TechDto {
    private Long id;
    private String techName;
    private TechCategories category;
    private float skillRating;
    private List<Long> usedInProjectIds;

    public TechDto() {}

    // Full object for GET
    public TechDto(Long id, String techName, TechCategories category, float skillRating, List<Long> usedInProjectIds) {
        this.id = id;
        this.techName = techName;
        this.category = category;
        this.skillRating = skillRating;
        this.usedInProjectIds = usedInProjectIds;
    }

    // without Id for POST
    public TechDto(String techName, TechCategories category, float skillRating, List<Long> usedInProjectIds) {
        this.techName = techName;
        this.category = category;
        this.skillRating = skillRating;
        this.usedInProjectIds = usedInProjectIds;
    }

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

    public List<Long> getUsedInProjectIds() {
        return usedInProjectIds;
    }

    public void setUsedInProjectIds(List<Long> usedInProjectIds) {
        this.usedInProjectIds = usedInProjectIds;
    }

}
