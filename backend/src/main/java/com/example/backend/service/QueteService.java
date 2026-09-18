package com.example.backend.service;

import com.example.backend.dto.QueteDTO;
import com.example.backend.entity.Quete;
import com.example.backend.mapper.QueteMapper;
import com.example.backend.repository.QueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Marks this class as a Spring service.
// The service contains the business logic related to quests.
@Service
public class QueteService {

    // Repository used to access quest data in the database.
    private final QueteRepository queteRepository;

    // Connects the service to the quest repository.
    // Spring automatically provides the repository.
    public QueteService(QueteRepository queteRepository) {
        this.queteRepository = queteRepository;
    }

    // Returns all quests.
    public List<QueteDTO> getAllQuetes() {

        // findAll() retrieves all quests from the database.
        // stream() allows us to process each quest.
        // map() converts each entity into a QueteDTO.
        // collect() converts the result back into a List.
        return queteRepository.findAll()
                .stream()
                .map(QueteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Returns a quest by its ID.
    public Optional<QueteDTO> getQueteById(Integer id) {

        // findById() searches for a quest using its ID.
        // map() converts the entity into a DTO if it exists.
        // Optional allows the result to be present or empty.
        return queteRepository.findById(id)
                .map(QueteMapper::toDTO);
    }

    // Creates a new quest.
    public QueteDTO createQuete(QueteDTO dto) {

        // Converts the DTO received from the API into a Quete entity.
        Quete quete = QueteMapper.toEntity(dto);

        // Saves the new quest in the database.
        Quete savedQuete = queteRepository.save(quete);

        // Converts the saved entity back into a DTO and returns it.
        return QueteMapper.toDTO(savedQuete);
    }

    // Updates an existing quest.
    public QueteDTO updateQuete(QueteDTO dto) {

        // Converts the DTO into a Quete entity.
        Quete quete = QueteMapper.toEntity(dto);

        // Saves the entity.
        // If the ID already exists, the existing quest is updated.
        Quete updatedQuete = queteRepository.save(quete);

        // Converts the updated entity back into a DTO and returns it.
        return QueteMapper.toDTO(updatedQuete);
    }

    // Deletes a quest by its ID.
    public void deleteQuete(Integer id) {

        // Deletes the quest from the database using its ID.
        queteRepository.deleteById(id);
    }
}