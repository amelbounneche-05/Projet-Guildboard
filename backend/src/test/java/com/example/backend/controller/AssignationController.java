package com.example.backend.controller;

import com.example.backend.dto.AssignationDTO;
import com.example.backend.service.AssignationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignations")
public class AssignationController {

    private final AssignationService assignationService;

    public AssignationController(AssignationService assignationService) {
        this.assignationService = assignationService;
    }

    // Retourne toutes les assignations
    @GetMapping
    public List<AssignationDTO> getAllAssignations() {
        return assignationService.getAllAssignations();
    }

    // Retourne une assignation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<AssignationDTO> getAssignationById(
            @PathVariable Integer id) {

        return assignationService.getAssignationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crée une nouvelle assignation
    @PostMapping
    public AssignationDTO createAssignation(
            @Valid @RequestBody AssignationDTO assignationDTO) {

        return assignationService.createAssignation(assignationDTO);
    }

    // Modifie une assignation existante
    @PutMapping("/{id}")
    public ResponseEntity<AssignationDTO> updateAssignation(
            @PathVariable Integer id,
            @Valid @RequestBody AssignationDTO assignationDTO) {

        if (assignationService.getAssignationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                assignationService.updateAssignation(id, assignationDTO)
        );
    }

    // Supprime une assignation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignation(
            @PathVariable Integer id) {

        if (assignationService.getAssignationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        assignationService.deleteAssignation(id);

        return ResponseEntity.noContent().build();
    }
}