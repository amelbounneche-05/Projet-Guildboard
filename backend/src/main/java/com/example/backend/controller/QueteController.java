package com.example.backend.controller;

import com.example.backend.dto.QueteDTO;
import com.example.backend.service.QueteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a REST controller.
// It handles HTTP requests related to quests.
@RestController

// Defines the base URL for all endpoints in this controller.
// All routes will start with /api/quetes.
@RequestMapping("/api/quetes")
public class QueteController {

    // Service used to execute the business logic for quests.
    private final QueteService queteService;

    // Connects the controller to the quest service.
    // The service is injected through the constructor.
    public QueteController(QueteService queteService) {
        this.queteService = queteService;
    }

    // GET /api/quetes
    // Returns all quests.
    @GetMapping
    public List<QueteDTO> getAllQuetes() {
        return queteService.getAllQuetes();
    }

    // GET /api/quetes/{id}
    // Returns a quest by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<QueteDTO> getQueteById(@PathVariable Integer id) {

        // @PathVariable gets the ID directly from the URL.
        // Returns HTTP 200 if the quest exists.
        // Returns HTTP 404 if the quest does not exist.
        return queteService.getQueteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/quetes
    // Creates a new quest.
    @PostMapping
    public QueteDTO createQuete(
            // @RequestBody converts the JSON request body into a QueteDTO.
            // @Valid checks that the received data respects the validation rules.
            @Valid @RequestBody QueteDTO queteDTO) {

        return queteService.createQuete(queteDTO);
    }

    // PUT /api/quetes/{id}
    // Updates an existing quest.
    @PutMapping("/{id}")
    public ResponseEntity<QueteDTO> updateQuete(
            // Gets the quest ID from the URL.
            @PathVariable Integer id,

            // Gets the new quest data from the JSON request body.
            // @Valid checks the validation rules.
            @Valid @RequestBody QueteDTO queteDTO) {

        // Checks if the quest exists before updating it.
        // Returns HTTP 404 if the quest does not exist.
        return queteService.getQueteById(id)
                .map(existingQuete -> {

                    // Sets the ID of the DTO to the ID provided in the URL.
                    queteDTO.setId(id);

                    // Updates the quest and returns HTTP 200
                    // with the updated quest.
                    return ResponseEntity.ok(
                            queteService.updateQuete(queteDTO));
                })

                // Returns HTTP 404 if no quest was found.
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/quetes/{id}
    // Deletes a quest by its ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuete(@PathVariable Integer id) {

        // Checks if the quest exists before deleting it.
        // Returns HTTP 404 if the quest does not exist.
        if (queteService.getQueteById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Deletes the quest from the database.
        queteService.deleteQuete(id);

        // Returns HTTP 204 to indicate that the deletion was successful.
        return ResponseEntity.noContent().build();
    }
}