package com.buildcircle.superheroes.characters;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CharacterResponse {

    private String name;
    private double score;
    private String type;

}
