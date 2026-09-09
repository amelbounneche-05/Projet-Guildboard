package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aventurier")
public class Aventurier {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Adventurer name
    @Column(nullable = false, unique = true, length = 50)
    private String nom;

    // Character class
    @Column(nullable = false, length = 50)
    private String classe;

    // Current level
    @Column(nullable = false)
    private Integer niveau;

    // Experience points
    @Column(nullable = false)
    private Integer xp;

    // Gold
    @Column(name = "\"or\"", nullable = false)
    private Integer or;

    // Empty constructor
    public Aventurier() {
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getOr() {
        return or;
    }

    public void setOr(Integer or) {
        this.or = or;
    }
}