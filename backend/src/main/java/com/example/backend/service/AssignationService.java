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

@Service
public class AssignationService {

    private final AssignationRepository assignationRepository;
    private final AventurierRepository aventurierRepository;
    private final QueteRepository queteRepository;

    // Connects the service to the repositories
    public AssignationService(
            AssignationRepository assignationRepository,
            AventurierRepository aventurierRepository,
            QueteRepository queteRepository) {

        this.assignationRepository = assignationRepository;
        this.aventurierRepository = aventurierRepository;
        this.queteRepository = queteRepository;
    }

    // Returns all assignments
    public List<AssignationDTO> getAllAssignations() {
        return assignationRepository.findAll()
                .stream()
                .map(AssignationMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Returns an assignment by its ID
    public Optional<AssignationDTO> getAssignationById(Integer id) {
        return assignationRepository.findById(id)
                .map(AssignationMapper::toDTO);
    }

    // Creates a new assignment
    public AssignationDTO createAssignation(AssignationDTO dto) {

        Assignation assignation = new Assignation();

        Aventurier aventurier = aventurierRepository
                .findById(dto.getAventurierId())
                .orElseThrow();

        Quete quete = queteRepository
                .findById(dto.getQueteId())
                .orElseThrow();

        assignation.setAventurier(aventurier);
        assignation.setQuete(quete);
        assignation.setDateAssignee(dto.getDateAssignee());
        assignation.setDateTerminee(dto.getDateTerminee());

        Assignation savedAssignation = assignationRepository.save(assignation);

        return AssignationMapper.toDTO(savedAssignation);
    }

    // Updates an existing assignment
    public AssignationDTO updateAssignation(Integer id, AssignationDTO dto) {

        Assignation assignation = assignationRepository
                .findById(id)
                .orElseThrow();

        if (dto.getAventurierId() != null) {
            Aventurier aventurier = aventurierRepository
                    .findById(dto.getAventurierId())
                    .orElseThrow();

            assignation.setAventurier(aventurier);
        }

        if (dto.getQueteId() != null) {
            Quete quete = queteRepository
                    .findById(dto.getQueteId())
                    .orElseThrow();

            assignation.setQuete(quete);
        }

        if (dto.getDateAssignee() != null) {
            assignation.setDateAssignee(dto.getDateAssignee());
        }

        assignation.setDateTerminee(dto.getDateTerminee());

        Assignation updatedAssignation = assignationRepository.save(assignation);

        return AssignationMapper.toDTO(updatedAssignation);
    }

    // Deletes an assignment by its ID
    public void deleteAssignation(Integer id) {
        assignationRepository.deleteById(id);
    }
}