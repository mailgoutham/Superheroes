package com.buildcircle.superheroes.exceptions;

public class CharacterNotFoundException extends RuntimeException{
    public CharacterNotFoundException(String message){
        super(message);
    }
}
