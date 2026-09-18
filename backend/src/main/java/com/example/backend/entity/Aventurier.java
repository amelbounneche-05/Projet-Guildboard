package com.example.backend.entity;

import jakarta.persistence.*;

// This class represents the Aventurier entity in the database.
@Entity

// Maps this entity to the "aventurier" table in PostgreSQL.
@Table(name = "aventurier")
public class Aventurier {

    // Primary key of the adventurer.
    @Id

    // The database automatically generates the ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Name of the adventurer.
    // nullable = false means this field cannot be NULL.
    // unique = true means two adventurers cannot have the same name.
    // length = 50 limits the maximum length to 50 characters.
    @Column(nullable = false, unique = true, length = 50)
    private String nom;

    // Class of the adventurer.
    // nullable = false means this field is required.
    // length = 50 limits the maximum length to 50 characters.
    @Column(nullable = false, length = 50)
    private String classe;

    // Current level of the adventurer.
    @Column(nullable = false)
    private Integer niveau;

    // Experience points of the adventurer.
    @Column(nullable = false)
    private Integer xp;

    // Amount of gold owned by the adventurer.
    // The column name is written with quotes because "or"
    // can cause conflicts with SQL keywords.
    @Column(name = "\"or\"", nullable = false)
    private Integer or;

    // Empty constructor required by JPA.
    public Aventurier() {
    }

    // Constructor used to create an adventurer with all its information.
    public Aventurier(String nom, String classe, Integer niveau, Integer xp, Integer or) {
        this.nom = nom;
        this.classe = classe;
        this.niveau = niveau;
        this.xp = xp;
        this.or = or;
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

    // Returns the adventurer experience points.
    public Integer getXp() {
        return xp;
    }

    // Sets the adventurer experience points.
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
