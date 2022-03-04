package com.example.demo_manytomany.exceptions;

public class BadRequest extends RuntimeException{
    private String message;
    public BadRequest(String message){
        super(message);
    }
}
