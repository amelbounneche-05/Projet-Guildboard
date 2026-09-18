package com.example.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// This class represents the Assignation entity in the database.
@Entity

// Maps this entity to the "assignation" table in PostgreSQL.
@Table(name = "assignation")
public class Assignation {

    // Primary key of the assignation.
    @Id

    // The database automatically generates the ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Date and time when the adventurer was assigned to the quest.
    // nullable = false means this field cannot be NULL in the database.
    @Column(name = "date_assignee", nullable = false)
    private LocalDateTime dateAssignee;

    // Date and time when the quest was completed.
    // This field can be NULL if the quest is not completed yet.
    @Column(name = "date_terminee")
    private LocalDateTime dateTerminee;

    // Relationship between an assignation and an adventurer.
    // Many assignations can belong to the same adventurer.
    @ManyToOne

    // Creates the foreign key "aventurier_id" in the assignation table.
    // nullable = false means an assignation must have an adventurer.
    @JoinColumn(name = "aventurier_id", nullable = false)
    private Aventurier aventurier;

    // Relationship between an assignation and a quest.
    // One quest can only have one assignation.
    @OneToOne

    // Creates the foreign key "quete_id".
    // nullable = false means an assignation must have a quest.
    // unique = true means the same quest cannot be assigned twice.
    @JoinColumn(name = "quete_id", nullable = false, unique = true)
    private Quete quete;

    // Empty constructor required by JPA.
    public Assignation() {
    }

    // This method is executed automatically before the entity is saved.
    @PrePersist
    public void prePersist() {

        // If no assignment date was provided,
        // the current date and time are automatically used.
        if (dateAssignee == null) {
            dateAssignee = LocalDateTime.now();
        }
    }

    // Getters and setters

    // Returns the assignation ID.
    public Integer getId() {
        return id;
    }

    // Sets the assignation ID.
    public void setId(Integer id) {
        this.id = id;
    }

    // Returns the assignment date.
    public LocalDateTime getDateAssignee() {
        return dateAssignee;
    }

    // Sets the assignment date.
    public void setDateAssignee(LocalDateTime dateAssignee) {
        this.dateAssignee = dateAssignee;
    }

    // Returns the completion date.
    public LocalDateTime getDateTerminee() {
        return dateTerminee;
    }

    // Sets the completion date.
    public void setDateTerminee(LocalDateTime dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    // Returns the assigned adventurer.
    public Aventurier getAventurier() {
        return aventurier;
    }

    // Sets the assigned adventurer.
    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }

    // Returns the assigned quest.
    public Quete getQuete() {
        return quete;
    }

    // Sets the assigned quest.
    public void setQuete(Quete quete) {
        this.quete = quete;
    }
}