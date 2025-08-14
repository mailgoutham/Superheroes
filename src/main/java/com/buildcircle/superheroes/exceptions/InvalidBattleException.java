package com.buildcircle.superheroes.exceptions;

public class InvalidBattleException extends RuntimeException{
    public InvalidBattleException(String message){
        super(message);
    }
}
