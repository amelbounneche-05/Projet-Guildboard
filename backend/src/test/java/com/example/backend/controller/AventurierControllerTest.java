package com.example.backend.controller;

import com.example.backend.entity.Aventurier;
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
public class AventurierControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    // Test GET all adventurers
    @Test
    public void testGetAllAventuriers() {

        ResponseEntity<Aventurier[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/aventuriers",
                Aventurier[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test GET one adventurer
    @Test
    public void testGetAventurierById() {

        ResponseEntity<Aventurier> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/aventuriers/7",
                Aventurier.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test POST create adventurer
    @Test
    public void testCreateAventurier() {

        Aventurier aventurier = new Aventurier();

        aventurier.setNom("TestAventurier" + System.currentTimeMillis());
        aventurier.setClasse("Guerrier");
        aventurier.setNiveau(1);
        aventurier.setXp(0);
        aventurier.setOr(100);

        HttpEntity<Aventurier> request = new HttpEntity<>(aventurier);

        ResponseEntity<Aventurier> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/aventuriers",
                request,
                Aventurier.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}