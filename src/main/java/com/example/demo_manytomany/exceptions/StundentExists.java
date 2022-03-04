package com.example.demo_manytomany.exceptions;

public class StundentExists extends RuntimeException{

    public StundentExists(String message){
        super(message);
    }
}
