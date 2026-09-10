package com.example.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AssignationDTO {

    private Integer id;

    private LocalDateTime dateAssignee;

    private LocalDateTime dateTerminee;

    @NotNull
    @Min(1)
    private Integer aventurierId;

    @NotNull
    @Min(1)
    private Integer queteId;

    public AssignationDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateAssignee() {
        return dateAssignee;
    }

    public void setDateAssignee(LocalDateTime dateAssignee) {
        this.dateAssignee = dateAssignee;
    }

    public LocalDateTime getDateTerminee() {
        return dateTerminee;
    }

    public void setDateTerminee(LocalDateTime dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    public Integer getAventurierId() {
        return aventurierId;
    }

    public void setAventurierId(Integer aventurierId) {
        this.aventurierId = aventurierId;
    }

    public Integer getQueteId() {
        return queteId;
    }

    public void setQueteId(Integer queteId) {
        this.queteId = queteId;
    }
}