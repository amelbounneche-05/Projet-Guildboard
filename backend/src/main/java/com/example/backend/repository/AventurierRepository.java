package com.example.backend.repository;

import com.example.backend.entity.Aventurier;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository used to access and manage Aventurier data in the database.
// It is connected to the Aventurier entity.
public interface AventurierRepository extends JpaRepository<Aventurier, Integer> {

    // JpaRepository automatically provides common database operations:
    // - save() -> create or update an Aventurier
    // - findAll() -> get all Aventuriers
    // - findById() -> get one Aventurier by its ID
    // - deleteById() -> delete an Aventurier by its ID
    // - existsById() -> check if an Aventurier exists
}