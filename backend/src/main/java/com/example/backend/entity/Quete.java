package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quete")
public class Quete {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Quest title
    @Column(nullable = false, unique = true, length = 100)
    private String titre;

    // Quest difficulty
    @Column(nullable = false, length = 20)
    private String difficulte;

    // Quest description
    @Column(nullable = false, length = 500)
    private String description;

    // Minimum level required
    @Column(name = "niveau_requis", nullable = false)
    private Integer niveauRequis;

    // Current quest status
    @Column(nullable = false, length = 20)
    private String statut;

    // Gold reward
    @Column(name = "recompense_or", nullable = false)
    private Integer recompenseOr;

    // Experience reward
    @Column(name = "recompense_xp", nullable = false)
    private Integer recompenseXp;

    // Empty constructor
    public Quete() {
    }

    // Getters and setters

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