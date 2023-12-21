package com.example.IntenertMagazin.controller;

import com.example.IntenertMagazin.exception.ProdusNotFoundException;
import com.example.IntenertMagazin.exception.ProdusUpdateException;
import com.example.IntenertMagazin.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProdusControllerHandler {
    @ExceptionHandler(ProdusNotFoundException.class)
    public ResponseEntity<Object> produsNotFoundException(ProdusNotFoundException produsNotFoundException){
        Map<String, Object> map = new HashMap<>();

        map.put("message", "Nu a fost gasit produsul cu id-ul dat! " + produsNotFoundException.getMessage());
        map.put("localTime", LocalDateTime.now());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
   @ExceptionHandler(ProdusUpdateException.class)
    public ResponseEntity<Object> produsUpdateException(ProdusUpdateException produsUpdateException){

        Map<String,Object> map = new HashMap<>();
        map.put("message", "A aparut o eroare la acutalizarea produsului! " + produsUpdateException.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object>validationException(ValidationException validationException){
        Map<String, Object> map = new HashMap<>();
        map.put("message", "A aparut una din erorile!" + validationException.getMessage());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validateDTO(MethodArgumentNotValidException exception){

        Map<String, Object> map = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error)->{
            map.put(((FieldError)error).getField(), error.getDefaultMessage());
        });


        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

    }

}
