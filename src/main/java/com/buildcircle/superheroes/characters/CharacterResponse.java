package com.buildcircle.superheroes.characters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CharacterResponse {

    public CharacterResponse(String name, double score, String type)
    {
        this.name = name;
        this.score = score;
        this.type = type;
    }

    public String name;
    public double score;
    public String type;

}
