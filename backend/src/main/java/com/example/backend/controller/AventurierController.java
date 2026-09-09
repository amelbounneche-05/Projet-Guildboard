package com.example.backend.controller;

import com.example.backend.entity.Aventurier;
import com.example.backend.service.AventurierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aventuriers")
public class AventurierController {

    private final AventurierService aventurierService;

    // Connects the controller to the adventurer service
    public AventurierController(AventurierService aventurierService) {
        this.aventurierService = aventurierService;
    }

    // Returns all adventurers
    @GetMapping
    public List<Aventurier> getAllAventuriers() {
        return aventurierService.getAllAventuriers();
    }

    // Returns an adventurer by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Aventurier> getAventurierById(@PathVariable Integer id) {
        return aventurierService.getAventurierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Creates a new adventurer
    @PostMapping
    public Aventurier createAventurier(@RequestBody Aventurier aventurier) {
        return aventurierService.createAventurier(aventurier);
    }

    // Updates an existing adventurer
    @PutMapping("/{id}")
    public ResponseEntity<Aventurier> updateAventurier(
            @PathVariable Integer id,
            @RequestBody Aventurier aventurier) {

        return aventurierService.getAventurierById(id)
                .map(existingAventurier -> {
                    aventurier.setId(id);
                    return ResponseEntity.ok(
                            aventurierService.updateAventurier(aventurier));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletes an adventurer by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAventurier(@PathVariable Integer id) {
        if (aventurierService.getAventurierById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        aventurierService.deleteAventurier(id);
        return ResponseEntity.noContent().build();
    }
}