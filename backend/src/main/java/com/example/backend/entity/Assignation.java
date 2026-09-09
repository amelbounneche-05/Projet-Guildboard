package com.example.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignation")
public class Assignation {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Assignment date
    @Column(name = "date_assignee", nullable = false)
    private LocalDateTime dateAssignee;

    // Completion date
    @Column(name = "date_terminee")
    private LocalDateTime dateTerminee;

    // Assigned adventurer
    @ManyToOne
    @JoinColumn(name = "aventurier_id", nullable = false)
    private Aventurier aventurier;

    // Assigned quest
    @OneToOne
    @JoinColumn(name = "quete_id", nullable = false, unique = true)
    private Quete quete;

    // Empty constructor
    public Assignation() {
    }

    // Sets the assignment date before saving
    @PrePersist
    public void prePersist() {
        if (dateAssignee == null) {
            dateAssignee = LocalDateTime.now();
        }
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateAssignee() {
        return dateAssignee;
    }

    public void setDateAssignee(LocalDateTime dateAssignee) {
        this.dateAssignee = dateAssignee;
    }

    public LocalDateTime getDateTerminee() {
        return dateTerminee;
    }

    public void setDateTerminee(LocalDateTime dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }

    public Quete getQuete() {
        return quete;
    }

    public void setQuete(Quete quete) {
        this.quete = quete;
    }
}