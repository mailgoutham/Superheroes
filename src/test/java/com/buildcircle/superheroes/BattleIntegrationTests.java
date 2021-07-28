package com.buildcircle.superheroes;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperheroesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BattleTests {

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
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK,"");
    }

}
