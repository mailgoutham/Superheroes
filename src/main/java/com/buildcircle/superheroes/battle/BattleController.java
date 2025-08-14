package com.buildcircle.superheroes.battle;
import com.buildcircle.superheroes.characters.CharacterResponse;
import com.buildcircle.superheroes.characters.CharactersProvider;
import com.buildcircle.superheroes.characters.CharactersResponse;
import com.buildcircle.superheroes.exceptions.CharacterNotFoundException;
import com.buildcircle.superheroes.exceptions.CharacterProviderException;
import com.buildcircle.superheroes.exceptions.ConflictCharacterTypeException;
import com.buildcircle.superheroes.exceptions.InvalidBattleException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService)
    {
        this.battleService = battleService;
    }

    @GetMapping("/battle")
    public ResponseEntity<CharacterResponse> battle(@RequestParam(value = "hero") final String hero,
                                         @RequestParam(value = "villain") final String villain) {

        return ResponseEntity.ok(battleService.battle(hero, villain));
    }

}
