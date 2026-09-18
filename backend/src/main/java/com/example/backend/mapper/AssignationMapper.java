package com.example.backend.mapper;

import com.example.backend.dto.AssignationDTO;
import com.example.backend.entity.Assignation;

// This class is used to convert an Assignation entity into an AssignationDTO.
public class AssignationMapper {

    // Converts an entity to a DTO.
    // The DTO is used to send data to the client/API.
    public static AssignationDTO toDTO(Assignation assignation) {

        // Creates a new empty DTO.
        AssignationDTO dto = new AssignationDTO();

        // Copies the assignation ID from the entity to the DTO.
        dto.setId(assignation.getId());

        // Copies the assignment date from the entity to the DTO.
        dto.setDateAssignee(assignation.getDateAssignee());

        // Copies the completion date from the entity to the DTO.
        dto.setDateTerminee(assignation.getDateTerminee());

        // Checks if an adventurer is associated with the assignation.
        if (assignation.getAventurier() != null) {

            // Gets the adventurer ID and puts it into the DTO.
            dto.setAventurierId(assignation.getAventurier().getId());
        }

        // Checks if a quest is associated with the assignation.
        if (assignation.getQuete() != null) {

            // Gets the quest ID and puts it into the DTO.
            dto.setQueteId(assignation.getQuete().getId());
        }

        // Returns the DTO containing the assignation data.
        return dto;
    }
}