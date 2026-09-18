package com.example.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

// DTO (Data Transfer Object) used to transfer assignation data
// between the client and the backend.
public class AssignationDTO {

    // Unique ID of the assignation.
    private Integer id;

    // Date and time when the adventurer was assigned to the quest.
    private LocalDateTime dateAssignee;

    // Date and time when the quest was completed.
    private LocalDateTime dateTerminee;

    // ID of the adventurer assigned to the quest.
    // @NotNull means this value cannot be null.
    // @Min(1) means the value must be at least 1.
    @NotNull
    @Min(1)
    private Integer aventurierId;

    // ID of the quest associated with the assignation.
    // @NotNull means this value cannot be null.
    // @Min(1) means the value must be at least 1.
    @NotNull
    @Min(1)
    private Integer queteId;

    // Empty constructor required for creating the DTO.
    public AssignationDTO() {
    }

    // Returns the assignation ID.
    public Integer getId() {
        return id;
    }

    // Sets the assignation ID.
    public void setId(Integer id) {
        this.id = id;
    }

    // Returns the assignment date.
    public LocalDateTime getDateAssignee() {
        return dateAssignee;
    }

    // Sets the assignment date.
    public void setDateAssignee(LocalDateTime dateAssignee) {
        this.dateAssignee = dateAssignee;
    }

    // Returns the completion date.
    public LocalDateTime getDateTerminee() {
        return dateTerminee;
    }

    // Sets the completion date.
    public void setDateTerminee(LocalDateTime dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    // Returns the adventurer ID.
    public Integer getAventurierId() {
        return aventurierId;
    }

    // Sets the adventurer ID.
    public void setAventurierId(Integer aventurierId) {
        this.aventurierId = aventurierId;
    }

    // Returns the quest ID.
    public Integer getQueteId() {
        return queteId;
    }

    // Sets the quest ID.
    public void setQueteId(Integer queteId) {
        this.queteId = queteId;
    }
}
