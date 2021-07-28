package com.buildcircle.superheroes.characters;

public class CharactersResponse {

    public CharactersResponse(CharacterResponse[] items)
    {
        this.items = items;
    }

    public CharacterResponse[] items;
}
