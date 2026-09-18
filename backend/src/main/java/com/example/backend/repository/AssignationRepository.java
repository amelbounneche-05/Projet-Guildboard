package com.example.backend.repository;

import com.example.backend.entity.Assignation;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository used to access the Assignation data in the database.
// JpaRepository provides ready-to-use CRUD operations.
public interface AssignationRepository extends JpaRepository<Assignation, Integer> {

}