package com.example.backend.controller;

import com.example.backend.dto.AssignationDTO;
import com.example.backend.service.AssignationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a REST controller.
// It handles HTTP requests related to assignations.
@RestController

// Defines the base URL for all endpoints in this controller.
// All routes will start with /api/assignations.
@RequestMapping("/api/assignations")
public class AssignationController {

    // Service used to execute the business logic for assignations.
    private final AssignationService assignationService;

    // Constructor used to inject the AssignationService into the controller.
    public AssignationController(AssignationService assignationService) {
        this.assignationService = assignationService;
    }

    // GET /api/assignations
    // Retrieves all assignations from the database.
    @GetMapping
    public List<AssignationDTO> getAllAssignations() {
        return assignationService.getAllAssignations();
    }

    // GET /api/assignations/{id}
    // Retrieves one assignation using its ID.
    @GetMapping("/{id}")
    public ResponseEntity<AssignationDTO> getAssignationById(
            @PathVariable Integer id) {

        // Returns HTTP 200 if the assignation exists.
        // Returns HTTP 404 if the assignation does not exist.
        return assignationService.getAssignationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/assignations
    // Creates a new assignation.
    @PostMapping
    public AssignationDTO createAssignation(
            // @Valid checks that the data received respects the validation rules.
            // @RequestBody converts the JSON request body into an AssignationDTO object.
            @Valid @RequestBody AssignationDTO assignationDTO) {

        return assignationService.createAssignation(assignationDTO);
    }

    // PUT /api/assignations/{id}
    // Updates an existing assignation using its ID.
    @PutMapping("/{id}")
    public ResponseEntity<AssignationDTO> updateAssignation(
            @PathVariable Integer id,
            @Valid @RequestBody AssignationDTO assignationDTO) {

        // Checks if the assignation exists before updating it.
        // Returns HTTP 404 if it does not exist.
        if (assignationService.getAssignationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Updates the assignation and returns HTTP 200 with the updated data.
        return ResponseEntity.ok(
                assignationService.updateAssignation(id, assignationDTO));
    }

    // DELETE /api/assignations/{id}
    // Deletes an assignation using its ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignation(
            @PathVariable Integer id) {

        // Checks if the assignation exists before deleting it.
        // Returns HTTP 404 if it does not exist.
        if (assignationService.getAssignationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Deletes the assignation from the database.
        assignationService.deleteAssignation(id);

        // Returns HTTP 204 to indicate that the deletion was successful.
        return ResponseEntity.noContent().build();
    }
}
