package com.example.backend.repository;

import com.example.backend.entity.Quete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueteRepository extends JpaRepository<Quete, Integer> {
}