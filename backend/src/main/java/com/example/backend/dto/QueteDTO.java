package com.example.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QueteDTO {

    private Integer id;

    @NotBlank
    @Size(max = 100)
    private String titre;

    @NotBlank
    @Size(max = 20)
    private String difficulte;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotNull
    @Min(1)
    private Integer niveauRequis;

    @NotBlank
    @Size(max = 20)
    private String statut;

    @NotNull
    @Min(0)
    private Integer recompenseOr;

    @NotNull
    @Min(1)
    private Integer recompenseXp;

    public QueteDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNiveauRequis() {
        return niveauRequis;
    }

    public void setNiveauRequis(Integer niveauRequis) {
        this.niveauRequis = niveauRequis;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getRecompenseOr() {
        return recompenseOr;
    }

    public void setRecompenseOr(Integer recompenseOr) {
        this.recompenseOr = recompenseOr;
    }

    public Integer getRecompenseXp() {
        return recompenseXp;
    }

    public void setRecompenseXp(Integer recompenseXp) {
        this.recompenseXp = recompenseXp;
    }
}