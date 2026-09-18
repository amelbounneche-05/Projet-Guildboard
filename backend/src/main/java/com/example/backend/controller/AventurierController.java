package com.example.backend.controller;

import com.example.backend.dto.AventurierDTO;
import com.example.backend.service.AventurierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a REST controller.
// It handles HTTP requests related to adventurers.
@RestController

// Defines the base URL for all endpoints in this controller.
// All routes will start with /api/aventuriers.
@RequestMapping("/api/aventuriers")
public class AventurierController {

    // Service used to execute the business logic for adventurers.
    private final AventurierService aventurierService;

    // Connects the controller to the adventurer service.
    // The service is injected through the constructor.
    public AventurierController(AventurierService aventurierService) {
        this.aventurierService = aventurierService;
    }

    // GET /api/aventuriers
    // Returns all adventurers.
    @GetMapping
    public List<AventurierDTO> getAllAventuriers() {
        return aventurierService.getAllAventuriers();
    }

    // GET /api/aventuriers/{id}
    // Returns an adventurer by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<AventurierDTO> getAventurierById(@PathVariable Integer id) {

        // @PathVariable gets the ID directly from the URL.
        // Returns HTTP 200 if the adventurer exists.
        // Returns HTTP 404 if the adventurer does not exist.
        return aventurierService.getAventurierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/aventuriers
    // Creates a new adventurer.
    @PostMapping
    public AventurierDTO createAventurier(
            // @RequestBody converts the JSON request body into an AventurierDTO.
            // @Valid checks that the received data respects the validation rules.
            @Valid @RequestBody AventurierDTO aventurierDTO) {

        return aventurierService.createAventurier(aventurierDTO);
    }

    // PUT /api/aventuriers/{id}
    // Updates an existing adventurer.
    @PutMapping("/{id}")
    public ResponseEntity<AventurierDTO> updateAventurier(
            // Gets the adventurer ID from the URL.
            @PathVariable Integer id,

            // Gets the new adventurer data from the JSON request body.
            // @Valid checks the validation rules.
            @Valid @RequestBody AventurierDTO aventurierDTO) {

        // Checks if the adventurer exists before updating it.
        // Returns HTTP 404 if the adventurer does not exist.
        return aventurierService.getAventurierById(id)
                .map(existingAventurier -> {

                    // Sets the ID of the DTO to the ID provided in the URL.
                    aventurierDTO.setId(id);

                    // Updates the adventurer and returns HTTP 200
                    // with the updated adventurer.
                    return ResponseEntity.ok(
                            aventurierService.updateAventurier(aventurierDTO));
                })

                // Returns HTTP 404 if no adventurer was found.
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/aventuriers/{id}
    // Deletes an adventurer by its ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAventurier(@PathVariable Integer id) {

        // Checks if the adventurer exists before deleting it.
        // Returns HTTP 404 if the adventurer does not exist.
        if (aventurierService.getAventurierById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Deletes the adventurer from the database.
        aventurierService.deleteAventurier(id);

        // Returns HTTP 204 to indicate that the deletion was successful.
        return ResponseEntity.noContent().build();
    }
}