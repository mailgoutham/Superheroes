package com.buildcircle.superheroes.characters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResponse {

    public CharacterResponse(String name, double score, String type, String weakness)
    {
        this.name = name;
        this.score = score;
        this.type = type;
        this.weakness = weakness;
    }

    public String name;
    public double score;
    public String type;

    @JsonProperty(required = false)
    public String weakness;
}
