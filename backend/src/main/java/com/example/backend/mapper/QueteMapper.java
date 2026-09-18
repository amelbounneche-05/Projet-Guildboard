package com.example.backend.mapper;

import com.example.backend.dto.QueteDTO;
import com.example.backend.entity.Quete;

// This class is used to convert data between the Quete entity and the QueteDTO.
public class QueteMapper {

    // Converts an entity to a DTO.
    // The DTO is used to transfer data between the backend and the client/API.
    public static QueteDTO toDTO(Quete quete) {

        // Creates a new empty DTO.
        QueteDTO dto = new QueteDTO();

        // Copies the quest ID from the entity to the DTO.
        dto.setId(quete.getId());

        // Copies the quest title from the entity to the DTO.
        dto.setTitre(quete.getTitre());

        // Copies the quest difficulty from the entity to the DTO.
        dto.setDifficulte(quete.getDifficulte());

        // Copies the quest description from the entity to the DTO.
        dto.setDescription(quete.getDescription());

        // Copies the required level from the entity to the DTO.
        dto.setNiveauRequis(quete.getNiveauRequis());

        // Copies the quest status from the entity to the DTO.
        dto.setStatut(quete.getStatut());

        // Copies the gold reward from the entity to the DTO.
        dto.setRecompenseOr(quete.getRecompenseOr());

        // Copies the XP reward from the entity to the DTO.
        dto.setRecompenseXp(quete.getRecompenseXp());

        // Returns the DTO containing the entity data.
        return dto;
    }

    // Converts a DTO to an entity.
    // The entity can then be used by the service and saved in the database.
    public static Quete toEntity(QueteDTO dto) {

        // Creates a new empty entity.
        Quete quete = new Quete();

        // Copies the quest ID from the DTO to the entity.
        quete.setId(dto.getId());

        // Copies the quest title from the DTO to the entity.
        quete.setTitre(dto.getTitre());

        // Copies the quest difficulty from the DTO to the entity.
        quete.setDifficulte(dto.getDifficulte());

        // Copies the quest description from the DTO to the entity.
        quete.setDescription(dto.getDescription());

        // Copies the required level from the DTO to the entity.
        quete.setNiveauRequis(dto.getNiveauRequis());

        // Copies the quest status from the DTO to the entity.
        quete.setStatut(dto.getStatut());

        // Copies the gold reward from the DTO to the entity.
        quete.setRecompenseOr(dto.getRecompenseOr());

        // Copies the XP reward from the DTO to the entity.
        quete.setRecompenseXp(dto.getRecompenseXp());

        // Returns the entity containing the DTO data.
        return quete;
    }
}