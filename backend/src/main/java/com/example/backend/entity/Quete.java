package com.example.backend.entity;

import jakarta.persistence.*;

// This class represents the Quest entity in the database.
@Entity

// Maps this entity to the "quete" table in PostgreSQL.
@Table(name = "quete")
public class Quete {

    // Primary key of the quest.
    @Id

    // The database automatically generates the ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Title of the quest.
    // nullable = false means the title is required.
    // unique = true means two quests cannot have the same title.
    // length = 100 limits the column to 100 characters.
    @Column(nullable = false, unique = true, length = 100)
    private String titre;

    // Difficulty of the quest.
    // nullable = false means the difficulty is required.
    // length = 20 limits the column to 20 characters.
    @Column(nullable = false, length = 20)
    private String difficulte;

    // Description of the quest.
    // nullable = false means the description is required.
    // length = 500 limits the column to 500 characters.
    @Column(nullable = false, length = 500)
    private String description;

    // Minimum level required to access the quest.
    // nullable = false means this value is required.
    @Column(name = "niveau_requis", nullable = false)
    private Integer niveauRequis;

    // Current status of the quest.
    // nullable = false means the status is required.
    // length = 20 limits the column to 20 characters.
    @Column(nullable = false, length = 20)
    private String statut;

    // Gold reward given when the quest is completed.
    // nullable = false means the reward is required.
    @Column(name = "recompense_or", nullable = false)
    private Integer recompenseOr;

    // Experience points reward given when the quest is completed.
    // nullable = false means the reward is required.
    @Column(name = "recompense_xp", nullable = false)
    private Integer recompenseXp;

    // Empty constructor required by JPA.
    public Quete() {
    }

    // Getters and setters

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