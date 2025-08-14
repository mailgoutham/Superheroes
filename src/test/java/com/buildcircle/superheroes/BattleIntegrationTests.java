package com.buildcircle.superheroes;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperheroesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BattleIntegrationTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + this.port + uri;
    }

    @Test
    void canGetHeroes() {
        ResponseEntity<String> response = getStringResponseEntity("Batman", "Harley Quinn");

        //Then
        Assert.assertEquals(HttpStatus.OK,  response.getStatusCode());
        Assert.assertEquals("""
                {"name":"Batman","score":8.3,"type":"hero","weakness":"Joker"}""", response.getBody());
    }

    @Test
    void cannotFightThemselves() {
        ResponseEntity<String> response = getStringResponseEntity("Batman", "Batman");

        //Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST,  response.getStatusCode());
        Assert.assertEquals("""
                Hero or Villain cannot be the same""", response.getBody());
    }

    @Test
    void cannotFightWhenEmptyCharactersPassed() {
        ResponseEntity<String> response = getStringResponseEntity("", "");

        //Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST,  response.getStatusCode());
        Assert.assertEquals("""
                Hero or Villain is empty""", response.getBody());
    }

    @Test
    void cannotFightWhenCharacterNotFound() {
        ResponseEntity<String> response = getStringResponseEntity("Crazy_I_Am_Not_Available", "Joker");

        //Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST,  response.getStatusCode());
        Assert.assertEquals("""
                Hero or Villain cannot be found""", response.getBody());
    }

    @Test
    void cannotFightWhenCharacterHaveSameType() {
        ResponseEntity<String> response = getStringResponseEntity("Harley Quinn", "Joker");

        //Then
        Assert.assertEquals(HttpStatus.CONFLICT,  response.getStatusCode());
        Assert.assertEquals("""
                Hero or Villain cannot be the same type""", response.getBody());
    }


    private ResponseEntity<String> getStringResponseEntity(String hero, String villain) {
        //Given
        HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());

        //When
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/battle?hero=" + hero + "&villain=" + villain),
                HttpMethod.GET, entity, String.class);
        return response;
    }

}
