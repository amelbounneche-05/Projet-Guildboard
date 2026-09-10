package com.example.backend.mapper;

import com.example.backend.dto.AssignationDTO;
import com.example.backend.entity.Assignation;

public class AssignationMapper {

    // Converts an entity to a DTO
    public static AssignationDTO toDTO(Assignation assignation) {
        AssignationDTO dto = new AssignationDTO();

        dto.setId(assignation.getId());
        dto.setDateAssignee(assignation.getDateAssignee());
        dto.setDateTerminee(assignation.getDateTerminee());

        if (assignation.getAventurier() != null) {
            dto.setAventurierId(assignation.getAventurier().getId());
        }

        if (assignation.getQuete() != null) {
            dto.setQueteId(assignation.getQuete().getId());
        }

        return dto;
    }
}