package com.example.backend.service;

import com.example.backend.entity.Quete;
import com.example.backend.repository.QueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueteService {

    private final QueteRepository queteRepository;

    // Connects the service to the quest repository
    public QueteService(QueteRepository queteRepository) {
        this.queteRepository = queteRepository;
    }

    // Returns all quests
    public List<Quete> getAllQuetes() {
        return queteRepository.findAll();
    }

    // Returns a quest by its ID
    public Optional<Quete> getQueteById(Integer id) {
        return queteRepository.findById(id);
    }

    // Creates a new quest
    public Quete createQuete(Quete quete) {
        return queteRepository.save(quete);
    }

    // Updates an existing quest
    public Quete updateQuete(Quete quete) {
        return queteRepository.save(quete);
    }

    // Deletes a quest by its ID
    public void deleteQuete(Integer id) {
        queteRepository.deleteById(id);
    }
}