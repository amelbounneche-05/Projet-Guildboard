package com.example.backend.service;

import com.example.backend.dto.QueteDTO;
import com.example.backend.entity.Quete;
import com.example.backend.mapper.QueteMapper;
import com.example.backend.repository.QueteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueteService {

    private final QueteRepository queteRepository;

    // Connects the service to the quest repository
    public QueteService(QueteRepository queteRepository) {
        this.queteRepository = queteRepository;
    }

    // Returns all quests
    public List<QueteDTO> getAllQuetes() {
        return queteRepository.findAll()
                .stream()
                .map(QueteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Returns a quest by its ID
    public Optional<QueteDTO> getQueteById(Integer id) {
        return queteRepository.findById(id)
                .map(QueteMapper::toDTO);
    }

    // Creates a new quest
    public QueteDTO createQuete(QueteDTO dto) {
        Quete quete = QueteMapper.toEntity(dto);
        Quete savedQuete = queteRepository.save(quete);

        return QueteMapper.toDTO(savedQuete);
    }

    // Updates an existing quest
    public QueteDTO updateQuete(QueteDTO dto) {
        Quete quete = QueteMapper.toEntity(dto);
        Quete updatedQuete = queteRepository.save(quete);

        return QueteMapper.toDTO(updatedQuete);
    }

    // Deletes a quest by its ID
    public void deleteQuete(Integer id) {
        queteRepository.deleteById(id);
    }
}