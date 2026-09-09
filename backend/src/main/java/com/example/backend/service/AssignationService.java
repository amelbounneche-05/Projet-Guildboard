package com.example.backend.service;

import com.example.backend.entity.Assignation;
import com.example.backend.repository.AssignationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignationService {

    private final AssignationRepository assignationRepository;

    // Connects the service to the assignment repository
    public AssignationService(AssignationRepository assignationRepository) {
        this.assignationRepository = assignationRepository;
    }

    // Returns all assignments
    public List<Assignation> getAllAssignations() {
        return assignationRepository.findAll();
    }

    // Returns an assignment by its ID
    public Optional<Assignation> getAssignationById(Integer id) {
        return assignationRepository.findById(id);
    }

    // Creates a new assignment
    public Assignation createAssignation(Assignation assignation) {
        return assignationRepository.save(assignation);
    }

    // Updates an existing assignment
    public Assignation updateAssignation(Assignation assignation) {
        return assignationRepository.save(assignation);
    }

    // Deletes an assignment by its ID
    public void deleteAssignation(Integer id) {
        assignationRepository.deleteById(id);
    }
}