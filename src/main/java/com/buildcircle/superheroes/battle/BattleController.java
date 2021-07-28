package com.buildcircle.superheroes.battle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BattleController {

    @GetMapping("/battle")
    public String getPlayer(@RequestParam(value = "hero") final String hero,
                            @RequestParam(value = "villain") final String villain) {
       // return new Player(playerId, "soofaloofa", "Kevin Sookocheff");
        return "";
    }

}
