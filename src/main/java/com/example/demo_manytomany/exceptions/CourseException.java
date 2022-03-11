package com.example.demo_manytomany.exceptions;

public class CourseException extends RuntimeException{
    private String message;
    public CourseException(String message){
        super(message);

    }
}
