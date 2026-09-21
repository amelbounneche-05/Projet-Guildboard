package com.example.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO (Data Transfer Object) used to transfer adventurer data
// between the client and the backend.
public class AventurierDTO {

    // Unique ID of the adventurer.
    private Integer id;

    // Name of the adventurer.
    // @NotBlank means the name cannot be null, empty or only spaces.
    // @Size(max = 50) 
    //limits the name to 50 characters.
    // @Size (min = 2)
    @NotBlank
    @Size(max = 8)
    private String nom;

    // Class of the adventurer.
    // @NotBlank means the class cannot be null, empty or only spaces.
    // @Size(max = 50) limits the class to 50 characters.
    @NotBlank
    @Size(max = 50)
    private String classe;

    // Level of the adventurer.
    // @NotNull means the value is required.
    // @Min(1) means the level must be at least 1.
    @NotNull
    @Min(1)
    private Integer niveau;

    // Experience points of the adventurer.
    // @NotNull means the value is required.
    // @Min(0) means XP cannot be negative.
    @NotNull
    @Min(0)
    private Integer xp;

    // Amount of gold owned by the adventurer.
    // @NotNull means the value is required.
    // @Min(0) means gold cannot be negative.
    @NotNull
    @Min(0)
    private Integer or;

    // Empty constructor required for creating the DTO.
    public AventurierDTO() {
    }

    // Returns the adventurer ID.
    public Integer getId() {
        return id;
    }

    // Sets the adventurer ID.
    public void setId(Integer id) {
        this.id = id;
    }

    // Returns the adventurer name.
    public String getNom() {
        return nom;
    }

    // Sets the adventurer name.
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Returns the adventurer class.
    public String getClasse() {
        return classe;
    }

    // Sets the adventurer class.
    public void setClasse(String classe) {
        this.classe = classe;
    }

    // Returns the adventurer level.
    public Integer getNiveau() {
        return niveau;
    }

    // Sets the adventurer level.
    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    // Returns the adventurer XP.
    public Integer getXp() {
        return xp;
    }

    // Sets the adventurer XP.
    public void setXp(Integer xp) {
        this.xp = xp;
    }

    // Returns the amount of gold owned by the adventurer.
    public Integer getOr() {
        return or;
    }

    // Sets the amount of gold owned by the adventurer.
    public void setOr(Integer or) {
        this.or = or;
    }
}
