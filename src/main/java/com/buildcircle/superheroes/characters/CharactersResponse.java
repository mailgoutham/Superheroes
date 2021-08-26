package com.buildcircle.superheroes.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharactersResponse {

    public CharactersResponse() {
    }

    public CharactersResponse(CharacterResponse[] items)
    {
        this.items = items;
    }

    public CharacterResponse[] items;
}
