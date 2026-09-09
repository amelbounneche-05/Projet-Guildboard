package com.example.backend.repository;

import com.example.backend.entity.Assignation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignationRepository extends JpaRepository<Assignation, Integer> {
}