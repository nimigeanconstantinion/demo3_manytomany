package com.example.demo_manytomany.services;

import com.example.demo_manytomany.exceptions.StudentException;
import com.example.demo_manytomany.model.Course;
import com.example.demo_manytomany.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties"
)

class StudentServicesTest2 {
    @Autowired
    private  StudentServices underTest;




    @AfterEach
    void tearDown(){
    }


    @Test
    void getAllStudents() {
       assertEquals(9,underTest.getAllStudents().size());
    }

    @Test
    void addStudent() {
    }

    @Test
    void removeStudent() {
    }

    @Test
    void addBook() {
    }

    @Test
    void removeBook() {
    }

    @Test
    void addEnrolment() {
    }

    @Test
    void removeEnrolment() {
        Student s=underTest.getUser("beatrice.kunde@gmail.com","123");
        Course c=s.getCourses().get(0);
        underTest.removeEnrolment(s,c);
    }

    @Test
    void addCourse() {
    }

    @Test
    void removeCourse() {
    }

    @Test
    void getUser() {
    StudentException st= Assertions.assertThrows(StudentException.class,()->underTest.getUser("kjkj","kkj"));
    assertEquals("Email didn't exist",st.getMessage());
    assertEquals("ron.wintheiser@hotmail.com",underTest.getUser("ron.wintheiser@hotmail.com","123").getEmail());
    }
}