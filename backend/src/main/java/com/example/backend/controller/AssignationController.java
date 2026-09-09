package com.example.backend.controller;

import com.example.backend.entity.Assignation;
import com.example.backend.service.AssignationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignations")
public class AssignationController {

    private final AssignationService assignationService;

    // Connects the controller to the assignment service
    public AssignationController(AssignationService assignationService) {
        this.assignationService = assignationService;
    }

    // Returns all assignments
    @GetMapping
    public List<Assignation> getAllAssignations() {
        return assignationService.getAllAssignations();
    }

    // Returns an assignment by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Assignation> getAssignationById(@PathVariable Integer id) {
        return assignationService.getAssignationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Creates a new assignment
    @PostMapping
    public Assignation createAssignation(@RequestBody Assignation assignation) {
        return assignationService.createAssignation(assignation);
    }

    // Updates an existing assignment
    @PutMapping("/{id}")
    public ResponseEntity<Assignation> updateAssignation(
            @PathVariable Integer id,
            @RequestBody Assignation assignation) {

        return assignationService.getAssignationById(id)
                .map(existingAssignation -> {

                    // Keep the existing assignment date if it is not provided
                    if (assignation.getDateAssignee() == null) {
                        assignation.setDateAssignee(
                                existingAssignation.getDateAssignee());
                    }

                    assignation.setId(id);

                    return ResponseEntity.ok(
                            assignationService.updateAssignation(assignation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletes an assignment by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignation(@PathVariable Integer id) {
        if (assignationService.getAssignationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        assignationService.deleteAssignation(id);
        return ResponseEntity.noContent().build();
    }
}