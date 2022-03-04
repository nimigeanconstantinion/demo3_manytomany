package com.example.demo_manytomany.exceptions;

public class NotFoundStudent extends RuntimeException{
    public NotFoundStudent(String message){
        super(message);
    }
}
