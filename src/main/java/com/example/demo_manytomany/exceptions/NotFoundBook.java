package com.example.demo_manytomany.exceptions;

public class NotFoundBook extends RuntimeException{
    public NotFoundBook(String message){
        super(message);
    }
}
