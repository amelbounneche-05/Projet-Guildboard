package com.example.backend.service;

import com.example.backend.dto.AssignationDTO;
import com.example.backend.entity.Assignation;
import com.example.backend.entity.Aventurier;
import com.example.backend.entity.Quete;
import com.example.backend.mapper.AssignationMapper;
import com.example.backend.repository.AssignationRepository;
import com.example.backend.repository.AventurierRepository;
import com.example.backend.repository.QueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Marks this class as a Spring service.
// The service contains the business logic related to assignments.
@Service
public class AssignationService {

    // Repository used to access assignment data in the database.
    private final AssignationRepository assignationRepository;

    // Repository used to access adventurer data in the database.
    private final AventurierRepository aventurierRepository;

    // Repository used to access quest data in the database.
    private final QueteRepository queteRepository;

    // Connects the service to the repositories.
    // Spring automatically provides the required repositories.
    public AssignationService(
            AssignationRepository assignationRepository,
            AventurierRepository aventurierRepository,
            QueteRepository queteRepository) {

        this.assignationRepository = assignationRepository;
        this.aventurierRepository = aventurierRepository;
        this.queteRepository = queteRepository;
    }

    // Returns all assignments.
    public List<AssignationDTO> getAllAssignations() {

        // findAll() retrieves all assignments from the database.
        // stream() allows us to process each assignment.
        // map() converts each Assignation entity into an AssignationDTO.
        // collect() converts the result back into a List.
        return assignationRepository.findAll()
                .stream()
                .map(AssignationMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Returns an assignment by its ID.
    public Optional<AssignationDTO> getAssignationById(Integer id) {

        // findById() searches for an assignment using its ID.
        // map() converts the entity into a DTO if it exists.
        // Optional allows the result to be present or empty.
        return assignationRepository.findById(id)
                .map(AssignationMapper::toDTO);
    }

    // Creates a new assignment.
    public AssignationDTO createAssignation(AssignationDTO dto) {

        // Creates a new empty Assignation entity.
        Assignation assignation = new Assignation();

        // Searches for the adventurer using the ID received in the DTO.
        // orElseThrow() throws an exception if the adventurer does not exist.
        Aventurier aventurier = aventurierRepository
                .findById(dto.getAventurierId())
                .orElseThrow();

        // Searches for the quest using the ID received in the DTO.
        // orElseThrow() throws an exception if the quest does not exist.
        Quete quete = queteRepository
                .findById(dto.getQueteId())
                .orElseThrow();

        // Connects the adventurer to the assignment.
        assignation.setAventurier(aventurier);

        // Connects the quest to the assignment.
        assignation.setQuete(quete);

        // Sets the assignment date.
        assignation.setDateAssignee(dto.getDateAssignee());

        // Sets the completion date.
        assignation.setDateTerminee(dto.getDateTerminee());

        // Saves the new assignment in the database.
        Assignation savedAssignation = assignationRepository.save(assignation);

        // Converts the saved entity into a DTO and returns it.
        return AssignationMapper.toDTO(savedAssignation);
    }

    // Updates an existing assignment.
    public AssignationDTO updateAssignation(Integer id, AssignationDTO dto) {

        // Searches for the assignment using its ID.
        // orElseThrow() throws an exception if it does not exist.
        Assignation assignation = assignationRepository
                .findById(id)
                .orElseThrow();

        // Checks if a new adventurer ID was provided.
        if (dto.getAventurierId() != null) {

            // Searches for the new adventurer in the database.
            Aventurier aventurier = aventurierRepository
                    .findById(dto.getAventurierId())
                    .orElseThrow();

            // Updates the adventurer linked to the assignment.
            assignation.setAventurier(aventurier);
        }

        // Checks if a new quest ID was provided.
        if (dto.getQueteId() != null) {

            // Searches for the new quest in the database.
            Quete quete = queteRepository
                    .findById(dto.getQueteId())
                    .orElseThrow();

            // Updates the quest linked to the assignment.
            assignation.setQuete(quete);
        }

        // Checks if a new assignment date was provided.
        if (dto.getDateAssignee() != null) {

            // Updates the assignment date.
            assignation.setDateAssignee(dto.getDateAssignee());
        }

        // Updates the completion date.
        assignation.setDateTerminee(dto.getDateTerminee());

        // Saves the updated assignment in the database.
        Assignation updatedAssignation = assignationRepository.save(assignation);

        // Converts the updated entity into a DTO and returns it.
        return AssignationMapper.toDTO(updatedAssignation);
    }

    // Deletes an assignment by its ID.
    public void deleteAssignation(Integer id) {

        // Deletes the assignment from the database using its ID.
        assignationRepository.deleteById(id);
    }
}