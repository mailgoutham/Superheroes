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
        //Given
        HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());

        //When
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/battle?hero=Batman&villain=Joker"),
                HttpMethod.GET, entity, String.class);

        //Then
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

}
