package com.buildcircle.superheroes.battle;

import com.buildcircle.superheroes.characters.CharacterResponse;
import com.buildcircle.superheroes.characters.CharactersProvider;
import com.buildcircle.superheroes.characters.CharactersResponse;
import com.buildcircle.superheroes.exceptions.CharacterNotFoundException;
import com.buildcircle.superheroes.exceptions.CharacterProviderException;
import com.buildcircle.superheroes.exceptions.ConflictCharacterTypeException;
import com.buildcircle.superheroes.exceptions.InvalidBattleException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class BattleService {
    private final CharactersProvider charactersProvider;
    private final WinnerLogicService winnerLogicService;

    public BattleService(CharactersProvider charactersProvider, WinnerLogicService winnerLogicService)
    {
        this.charactersProvider = charactersProvider;
        this.winnerLogicService = winnerLogicService;
    }

    public CharacterResponse battle(String hero, String villain){
        if(! StringUtils.hasText(hero) || ! StringUtils.hasText(villain)){
            throw new InvalidBattleException("Hero or Villain is empty");
        }
        if(hero.equals(villain)){
            throw new InvalidBattleException("Hero or Villain cannot be the same");
        }
        CharactersResponse characters;
        try {
            characters = charactersProvider.getCharacters();
        } catch (IOException | InterruptedException e) {
            throw new CharacterProviderException(e.getMessage());
        }

        CharacterResponse character1 = null;
        CharacterResponse character2 = null;

        for (CharacterResponse character : characters.getItems()) {
            if(character.getName().equals(hero))
            {
                character1 = character;
            }

            if(character.getName().equals(villain))
            {
                character2 = character;
            }
            if(character1 !=null && character2 !=null){
                break;
            }
        }
        if(character1 == null || character2 == null){
            throw new CharacterNotFoundException("Hero or Villain cannot be found");
        }
        if(character1.getType().equals(character2.getType())){
            throw new ConflictCharacterTypeException("Hero or Villain cannot be the same type");
        }
       return winnerLogicService.decideWinner(character1, character2);
    }
}
