package com.buildcircle.superheroes.battle;
import com.buildcircle.superheroes.characters.CharacterResponse;
import com.buildcircle.superheroes.characters.CharactersProvider;
import com.buildcircle.superheroes.characters.CharactersResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BattleController {

    @Autowired
    private final CharactersProvider charactersProvider;

    private static CharacterResponse character1;
    private static CharacterResponse character2;

    public BattleController(CharactersProvider charactersProvider)
    {
        this.charactersProvider = charactersProvider;
    }

    @GetMapping("/battle")
    public String battle(@RequestParam(value = "hero") final String hero,
                            @RequestParam(value = "villain") final String villain) throws IOException, InterruptedException {

        CharactersResponse characters = charactersProvider.getCharacters();

        for (CharacterResponse character : characters.items) {
            if(character.name.equals(hero))
            {
                character1 = character;
            }

            if(character.name.equals(villain))
            {
                character2 = character;
            }
        }

        if(character1.score > character2.score)
        {
            return new Gson().toJson(character1);
        }

        return new Gson().toJson(character2);
    }

}
