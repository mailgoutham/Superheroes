package com.buildcircle.superheroes.characters;
import java.io.IOException;

public interface CharactersProvider {
    CharactersResponse getCharacters() throws IOException, InterruptedException;
}
