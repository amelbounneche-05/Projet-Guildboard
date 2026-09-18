package com.example.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO (Data Transfer Object) used to transfer quest data
// between the client and the backend.
public class QueteDTO {

    // Unique ID of the quest.
    private Integer id;

    // Title of the quest.
    // @NotBlank means the title cannot be null, empty or only spaces.
    // @Size(max = 100) limits the title to 100 characters.
    @NotBlank
    @Size(max = 100)
    private String titre;

    // Difficulty level of the quest.
    // @NotBlank means the difficulty cannot be null, empty or only spaces.
    // @Size(max = 20) limits the difficulty to 20 characters.
    @NotBlank
    @Size(max = 20)
    private String difficulte;

    // Description of the quest.
    // @NotBlank means the description cannot be null, empty or only spaces.
    // @Size(max = 500) limits the description to 500 characters.
    @NotBlank
    @Size(max = 500)
    private String description;

    // Minimum level required to complete the quest.
    // @NotNull means the value is required.
    // @Min(1) means the required level must be at least 1.
    @NotNull
    @Min(1)
    private Integer niveauRequis;

    // Current status of the quest.
    // @NotBlank means the status cannot be null, empty or only spaces.
    // @Size(max = 20) limits the status to 20 characters.
    @NotBlank
    @Size(max = 20)
    private String statut;

    // Gold reward given for completing the quest.
    // @NotNull means the value is required.
    // @Min(0) means the gold reward cannot be negative.
    @NotNull
    @Min(0)
    private Integer recompenseOr;

    // XP reward given for completing the quest.
    // @NotNull means the value is required.
    // @Min(1) means the XP reward must be at least 1.
    @NotNull
    @Min(1)
    private Integer recompenseXp;

    // Empty constructor required for creating the DTO.
    public QueteDTO() {
    }

    // Returns the quest ID.
    public Integer getId() {
        return id;
    }

    // Sets the quest ID.
    public void setId(Integer id) {
        this.id = id;
    }

    // Returns the quest title.
    public String getTitre() {
        return titre;
    }

    // Sets the quest title.
    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Returns the quest difficulty.
    public String getDifficulte() {
        return difficulte;
    }

    // Sets the quest difficulty.
    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    // Returns the quest description.
    public String getDescription() {
        return description;
    }

    // Sets the quest description.
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns the required level for the quest.
    public Integer getNiveauRequis() {
        return niveauRequis;
    }

    // Sets the required level for the quest.
    public void setNiveauRequis(Integer niveauRequis) {
        this.niveauRequis = niveauRequis;
    }

    // Returns the current quest status.
    public String getStatut() {
        return statut;
    }

    // Sets the quest status.
    public void setStatut(String statut) {
        this.statut = statut;
    }

    // Returns the gold reward.
    public Integer getRecompenseOr() {
        return recompenseOr;
    }

    // Sets the gold reward.
    public void setRecompenseOr(Integer recompenseOr) {
        this.recompenseOr = recompenseOr;
    }

    // Returns the XP reward.
    public Integer getRecompenseXp() {
        return recompenseXp;
    }

    // Sets the XP reward.
    public void setRecompenseXp(Integer recompenseXp) {
        this.recompenseXp = recompenseXp;
    }
}
