package com.buildcircle.superheroes;

import com.buildcircle.superheroes.battle.BattleService;
import com.buildcircle.superheroes.battle.WinnerLogicService;
import com.buildcircle.superheroes.characters.CharacterResponse;
import com.buildcircle.superheroes.characters.CharactersResponse;
import com.buildcircle.superheroes.exceptions.CharacterNotFoundException;
import com.buildcircle.superheroes.exceptions.CharacterProviderException;
import com.buildcircle.superheroes.exceptions.ConflictCharacterTypeException;
import com.buildcircle.superheroes.exceptions.InvalidBattleException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BattleUnitTests {

    FakeCharactersProvider fakeProvider;
    BattleService battleService;
    WinnerLogicService winnerLogicService;
    @Mock
    FakeCharactersProvider mockFakeCharactersProvider;

    @BeforeEach
    void setUp() throws IOException {
        CharacterResponse[] responses = new CharacterResponse[3];
        responses[0] = new CharacterResponse("Batman",8.3,"hero", "Joker");
        responses[1] = new CharacterResponse("Joker",8.2,"villain", null);
        responses[2] = new CharacterResponse("Superman",9.6,"hero", "Lex Luthor");
        CharactersResponse fakeResponse = new CharactersResponse(responses);

        fakeProvider = new FakeCharactersProvider(fakeResponse);
        battleService = new BattleService(fakeProvider, new WinnerLogicService());
    }

    @Test
    void canGetHeroes() {
        //Given
        String hero = "Superman";
        String villain = "Joker";

        //When
        CharacterResponse characterResponse = battleService.battle(hero,villain);

        //Then
        Assert.assertTrue(characterResponse.getName().equals(hero));
    }

    @Test
    void canGetHeroesWithWeakness() {
        //Given
        String hero = "Batman";
        String villain = "Joker";

        //When
        CharacterResponse characterResponse = battleService.battle(hero,villain);

        //Then
        Assert.assertTrue(characterResponse.getName().equals(villain));
    }

    @Test
    void cannotFightThemselves() {
        //Given
        String hero = "Batman";

        //Then
        Assert.assertThrows(InvalidBattleException.class, () -> battleService.battle(hero,hero));
    }

    @Test
    void cannotFightWhenEmptyCharactersPassed() {
        //Given
        String hero = "Batman";

        //Then
        Assert.assertThrows(InvalidBattleException.class, () -> battleService.battle(null,hero));
        Assert.assertThrows(InvalidBattleException.class, () -> battleService.battle(hero,null));
    }

    @Test
    void cannotFightWhenCharacterNotFound() {
        //Given
        String hero = "Crazy_I_Am_Not_Available";
        String villain = "Joker";

        //Then
        Assert.assertThrows(CharacterNotFoundException.class, () -> battleService.battle(hero, villain));
    }
    @Test
    void cannotFightWhenCharacterSameType() {
        //Given
        String hero = "Batman";
        String villain = "Superman";

        //Then
        Assert.assertThrows(ConflictCharacterTypeException.class, () -> battleService.battle(hero, villain));
    }

    @Test
    void serverErrorFromAWS() throws IOException, InterruptedException {
        //Given
        String hero = "Crazy_I_Am_Not_Available";
        String villain = "Joker";
        battleService = new BattleService(mockFakeCharactersProvider, new WinnerLogicService());
        when(mockFakeCharactersProvider.getCharacters()).thenThrow(new CharacterProviderException("Server Error"));
        //Then
        Assert.assertThrows(CharacterProviderException.class, () -> battleService.battle(hero, villain));
    }

}
