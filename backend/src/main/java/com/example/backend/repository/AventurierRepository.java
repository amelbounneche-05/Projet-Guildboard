package com.example.backend.repository;

import com.example.backend.entity.Aventurier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AventurierRepository extends JpaRepository<Aventurier, Integer> {
}