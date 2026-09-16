package com.example.backend.controller;

import com.example.backend.entity.Quete;
import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueteControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    // Test GET all quests
    @Test
    public void testGetAllQuetes() {

        ResponseEntity<Quete[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/quetes",
                Quete[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test GET one quest
    @Test
    public void testGetQueteById() {

        ResponseEntity<Quete> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/quetes/1",
                Quete.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test POST create quest
    @Test
    public void testCreateQuete() {

        Quete quete = new Quete();

        quete.setTitre("TestQuete" + System.currentTimeMillis());
        quete.setDifficulte("Facile");
        quete.setDescription("Quete de test pour les tests automatises");
        quete.setNiveauRequis(1);
        quete.setStatut("DISPONIBLE");
        quete.setRecompenseOr(100);
        quete.setRecompenseXp(50);

        HttpEntity<Quete> request = new HttpEntity<>(quete);

        ResponseEntity<Quete> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/quetes",
                request,
                Quete.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}