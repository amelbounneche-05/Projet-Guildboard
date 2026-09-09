package com.example.backend.service;

import com.example.backend.entity.Aventurier;
import com.example.backend.repository.AventurierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AventurierService {

    private final AventurierRepository aventurierRepository;

    // Connects the service to the adventurer repository
    public AventurierService(AventurierRepository aventurierRepository) {
        this.aventurierRepository = aventurierRepository;
    }

    // Returns all adventurers
    public List<Aventurier> getAllAventuriers() {
        return aventurierRepository.findAll();
    }

    // Returns an adventurer by its ID
    public Optional<Aventurier> getAventurierById(Integer id) {
        return aventurierRepository.findById(id);
    }

    // Creates a new adventurer
    public Aventurier createAventurier(Aventurier aventurier) {
        return aventurierRepository.save(aventurier);
    }

    // Updates an existing adventurer
    public Aventurier updateAventurier(Aventurier aventurier) {
        return aventurierRepository.save(aventurier);
    }

    // Deletes an adventurer by its ID
    public void deleteAventurier(Integer id) {
        aventurierRepository.deleteById(id);
    }
}