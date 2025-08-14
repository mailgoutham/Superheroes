package com.buildcircle.superheroes.battle;

import com.buildcircle.superheroes.characters.CharacterResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WinnerLogicService {

    public CharacterResponse decideWinner(CharacterResponse hero, CharacterResponse villain) {
        double heroScore = hero.getScore();
        if (StringUtils.hasText(hero.getWeakness()) && hero.getWeakness().equals(villain.getName())) {
            heroScore -= 1.0;
        }
        return (heroScore > villain.getScore()) ? hero : villain;
    }
}
