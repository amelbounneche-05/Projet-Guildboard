package com.example.backend.mapper;

import com.example.backend.dto.AventurierDTO;
import com.example.backend.entity.Aventurier;

// This class is used to convert data between the Aventurier entity and the AventurierDTO.
public class AventurierMapper {

    // Converts an entity to a DTO.
    // The DTO is used to transfer data between the backend and the client/API.
    public static AventurierDTO toDTO(Aventurier aventurier) {

        // Creates a new empty DTO.
        AventurierDTO dto = new AventurierDTO();

        // Copies the ID from the entity to the DTO.
        dto.setId(aventurier.getId());

        // Copies the adventurer name from the entity to the DTO.
        dto.setNom(aventurier.getNom());

        // Copies the character class from the entity to the DTO.
        dto.setClasse(aventurier.getClasse());

        // Copies the level from the entity to the DTO.
        dto.setNiveau(aventurier.getNiveau());

        // Copies the experience points from the entity to the DTO.
        dto.setXp(aventurier.getXp());

        // Copies the gold amount from the entity to the DTO.
        dto.setOr(aventurier.getOr());

        // Returns the DTO containing the entity data.
        return dto;
    }

    // Converts a DTO to an entity.
    // The entity can then be used by the service and saved in the database.
    public static Aventurier toEntity(AventurierDTO dto) {

        // Creates a new empty entity.
        Aventurier aventurier = new Aventurier();

        // Copies the ID from the DTO to the entity.
        aventurier.setId(dto.getId());

        // Copies the adventurer name from the DTO to the entity.
        aventurier.setNom(dto.getNom());

        // Copies the character class from the DTO to the entity.
        aventurier.setClasse(dto.getClasse());

        // Copies the level from the DTO to the entity.
        aventurier.setNiveau(dto.getNiveau());

        // Copies the experience points from the DTO to the entity.
        aventurier.setXp(dto.getXp());

        // Copies the gold amount from the DTO to the entity.
        aventurier.setOr(dto.getOr());

        // Returns the entity containing the DTO data.
        return aventurier;
    }
}