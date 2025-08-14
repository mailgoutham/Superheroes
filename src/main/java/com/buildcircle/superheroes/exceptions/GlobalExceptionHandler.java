package com.buildcircle.superheroes.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidBattleException.class)
    public ResponseEntity<String> invalidBattleException(Exception e,  HttpServletRequest request) {
        log.error("Invalid battle for "+ request.getQueryString() +" : {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CharacterProviderException.class)
    public ResponseEntity<String> characterProviderException(Exception e,  HttpServletRequest request) {
        log.error("Invalid battle for "+ request.getQueryString() +" : {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<String> characterNotFoundException(Exception e,  HttpServletRequest request) {
        log.error("Invalid battle for "+ request.getQueryString() +" : {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConflictCharacterTypeException.class)
    public ResponseEntity<String> conflictCharacterTypeException(Exception e, HttpServletRequest request) {
        log.error("Invalid battle for "+ request.getQueryString() +" : {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
