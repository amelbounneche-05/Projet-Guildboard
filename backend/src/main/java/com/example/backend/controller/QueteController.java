package com.example.backend.controller;

import com.example.backend.entity.Quete;
import com.example.backend.service.QueteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quetes")
public class QueteController {

    private final QueteService queteService;

    // Connects the controller to the quest service
    public QueteController(QueteService queteService) {
        this.queteService = queteService;
    }

    // Returns all quests
    @GetMapping
    public List<Quete> getAllQuetes() {
        return queteService.getAllQuetes();
    }

    // Returns a quest by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Quete> getQueteById(@PathVariable Integer id) {
        return queteService.getQueteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Creates a new quest
    @PostMapping
    public Quete createQuete(@RequestBody Quete quete) {
        return queteService.createQuete(quete);
    }

    // Updates an existing quest
    @PutMapping("/{id}")
    public ResponseEntity<Quete> updateQuete(
            @PathVariable Integer id,
            @RequestBody Quete quete) {

        return queteService.getQueteById(id)
                .map(existingQuete -> {
                    quete.setId(id);
                    return ResponseEntity.ok(
                            queteService.updateQuete(quete));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletes a quest by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuete(@PathVariable Integer id) {
        if (queteService.getQueteById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        queteService.deleteQuete(id);
        return ResponseEntity.noContent().build();
    }
}