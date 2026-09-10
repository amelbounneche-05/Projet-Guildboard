package com.example.backend.mapper;

import com.example.backend.dto.QueteDTO;
import com.example.backend.entity.Quete;

public class QueteMapper {

    // Converts an entity to a DTO
    public static QueteDTO toDTO(Quete quete) {
        QueteDTO dto = new QueteDTO();

        dto.setId(quete.getId());
        dto.setTitre(quete.getTitre());
        dto.setDifficulte(quete.getDifficulte());
        dto.setDescription(quete.getDescription());
        dto.setNiveauRequis(quete.getNiveauRequis());
        dto.setStatut(quete.getStatut());
        dto.setRecompenseOr(quete.getRecompenseOr());
        dto.setRecompenseXp(quete.getRecompenseXp());

        return dto;
    }

    // Converts a DTO to an entity
    public static Quete toEntity(QueteDTO dto) {
        Quete quete = new Quete();

        quete.setId(dto.getId());
        quete.setTitre(dto.getTitre());
        quete.setDifficulte(dto.getDifficulte());
        quete.setDescription(dto.getDescription());
        quete.setNiveauRequis(dto.getNiveauRequis());
        quete.setStatut(dto.getStatut());
        quete.setRecompenseOr(dto.getRecompenseOr());
        quete.setRecompenseXp(dto.getRecompenseXp());

        return quete;
    }
}