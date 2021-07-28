package com.buildcircle.superheroes;

import com.buildcircle.superheroes.characters.CharactersProvider;
import com.buildcircle.superheroes.characters.CharactersResponse;

import java.io.IOException;

public class FakeCharactersProvider implements CharactersProvider {

    CharactersResponse fakeResponse;

    public FakeCharactersProvider(CharactersResponse fakeResponse)
    {
        this.fakeResponse = fakeResponse;
    }

    @Override
    public CharactersResponse getCharacters() throws IOException, InterruptedException {
        return fakeResponse;
    }
}
