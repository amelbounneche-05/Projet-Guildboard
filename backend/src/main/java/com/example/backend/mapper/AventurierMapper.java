package com.example.backend.mapper;

import com.example.backend.dto.AventurierDTO;
import com.example.backend.entity.Aventurier;

public class AventurierMapper {

    // Converts an entity to a DTO
    public static AventurierDTO toDTO(Aventurier aventurier) {
        AventurierDTO dto = new AventurierDTO();

        dto.setId(aventurier.getId());
        dto.setNom(aventurier.getNom());
        dto.setClasse(aventurier.getClasse());
        dto.setNiveau(aventurier.getNiveau());
        dto.setXp(aventurier.getXp());
        dto.setOr(aventurier.getOr());

        return dto;
    }

    // Converts a DTO to an entity
    public static Aventurier toEntity(AventurierDTO dto) {
        Aventurier aventurier = new Aventurier();

        aventurier.setId(dto.getId());
        aventurier.setNom(dto.getNom());
        aventurier.setClasse(dto.getClasse());
        aventurier.setNiveau(dto.getNiveau());
        aventurier.setXp(dto.getXp());
        aventurier.setOr(dto.getOr());

        return aventurier;
    }
}