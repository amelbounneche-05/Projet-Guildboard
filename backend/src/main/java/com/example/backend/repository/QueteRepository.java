package com.example.backend.repository;

import com.example.backend.entity.Quete;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository used to access and manage Quete data in the database.
// It is connected to the Quete entity.
public interface QueteRepository extends JpaRepository<Quete, Integer> {

    // JpaRepository automatically provides common database operations:
    // - save() -> create or update a Quete
    // - findAll() -> get all Quetes
    // - findById() -> get one Quete by its ID
    // - deleteById() -> delete a Quete by its ID
    // - existsById() -> check if a Quete exists
}