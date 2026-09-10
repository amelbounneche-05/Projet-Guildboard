package com.example.backend.service;

import com.example.backend.dto.AventurierDTO;
import com.example.backend.entity.Aventurier;
import com.example.backend.mapper.AventurierMapper;
import com.example.backend.repository.AventurierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AventurierService {

    private final AventurierRepository aventurierRepository;

    // Connects the service to the adventurer repository
    public AventurierService(AventurierRepository aventurierRepository) {
        this.aventurierRepository = aventurierRepository;
    }

    // Returns all adventurers
    public List<AventurierDTO> getAllAventuriers() {
        return aventurierRepository.findAll()
                .stream()
                .map(AventurierMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Returns an adventurer by its ID
    public Optional<AventurierDTO> getAventurierById(Integer id) {
        return aventurierRepository.findById(id)
                .map(AventurierMapper::toDTO);
    }

    // Creates a new adventurer
    public AventurierDTO createAventurier(AventurierDTO dto) {
        Aventurier aventurier = AventurierMapper.toEntity(dto);
        Aventurier savedAventurier = aventurierRepository.save(aventurier);

        return AventurierMapper.toDTO(savedAventurier);
    }

    // Updates an existing adventurer
    public AventurierDTO updateAventurier(AventurierDTO dto) {
        Aventurier aventurier = AventurierMapper.toEntity(dto);
        Aventurier updatedAventurier = aventurierRepository.save(aventurier);

        return AventurierMapper.toDTO(updatedAventurier);
    }

    // Deletes an adventurer by its ID
    public void deleteAventurier(Integer id) {
        aventurierRepository.deleteById(id);
    }
}