package com.example.backend.service;

import com.example.backend.dto.AventurierDTO;
import com.example.backend.entity.Aventurier;
import com.example.backend.mapper.AventurierMapper;
import com.example.backend.repository.AventurierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Marks this class as a Spring service.
// The service contains the business logic related to adventurers.
@Service
public class AventurierService {

    // Repository used to access adventurer data in the database.
    private final AventurierRepository aventurierRepository;

    // Connects the service to the adventurer repository.
    // Spring automatically provides the repository.
    public AventurierService(AventurierRepository aventurierRepository) {
        this.aventurierRepository = aventurierRepository;
    }

    // Returns all adventurers.
    public List<AventurierDTO> getAllAventuriers() {

        // findAll() retrieves all adventurers from the database.
        // stream() allows us to process each adventurer.
        // map() converts each entity into an AventurierDTO.
        // collect() converts the result back into a List.
        return aventurierRepository.findAll()
                .stream()
                .map(AventurierMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Returns an adventurer by its ID.
    public Optional<AventurierDTO> getAventurierById(Integer id) {

        // findById() searches for an adventurer using its ID.
        // map() converts the entity into a DTO if it exists.
        // Optional allows the result to be present or empty.
        return aventurierRepository.findById(id)
                .map(AventurierMapper::toDTO);
    }

    // Creates a new adventurer.
    public AventurierDTO createAventurier(AventurierDTO dto) {

        // Converts the DTO received from the API into an entity.
        Aventurier aventurier = AventurierMapper.toEntity(dto);

        // Saves the new adventurer in the database.
        Aventurier savedAventurier = aventurierRepository.save(aventurier);

        // Converts the saved entity back into a DTO and returns it.
        return AventurierMapper.toDTO(savedAventurier);
    }

    // Updates an existing adventurer.
    public AventurierDTO updateAventurier(AventurierDTO dto) {

        // Converts the DTO into an Aventurier entity.
        Aventurier aventurier = AventurierMapper.toEntity(dto);

        // Saves the entity.
        // If the ID already exists, the existing adventurer is updated.
        Aventurier updatedAventurier = aventurierRepository.save(aventurier);

        // Converts the updated entity back into a DTO and returns it.
        return AventurierMapper.toDTO(updatedAventurier);
    }

    // Deletes an adventurer by its ID.
    public void deleteAventurier(Integer id) {

        // Deletes the adventurer from the database using its ID.
        aventurierRepository.deleteById(id);
    }
}