package com.buildcircle.superheroes;

import com.buildcircle.superheroes.battle.BattleController;
import com.buildcircle.superheroes.characters.CharacterResponse;
import com.buildcircle.superheroes.characters.CharactersResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class BattleUnitTests {

    FakeCharactersProvider fakeProvider;
    BattleController battleController;

    @Test
    void canGetHeroes() throws IOException, InterruptedException {

        CharacterResponse[] responses = new CharacterResponse[2];
        responses[0] = new CharacterResponse("Batman",8.3,"hero");
        responses[1] = new CharacterResponse("Joker",8.2,"villain");

        CharactersResponse fakeResponse = new CharactersResponse(responses);

        fakeProvider = new FakeCharactersProvider(fakeResponse);
        battleController = new BattleController(fakeProvider);

        //Given
        String hero = "Batman";
        String villain = "Joker";

        //When
        String responseBody = battleController.battle(hero,villain);

        //Then
        Assert.assertTrue(responseBody.equals("{\"name\":\"Batman\",\"score\":8.3,\"type\":\"hero\"}"));
    }

}
