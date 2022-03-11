package com.example.demo_manytomany.web;

import com.example.demo_manytomany.model.Student;
import com.example.demo_manytomany.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo")
@CrossOrigin
public class StudentControler {

        StudentServices studentServices;


        @Autowired
        public StudentControler(StudentServices studentServices){
            this.studentServices=studentServices;
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping
        public List<Student> getAllStudents(){

            return studentServices.getAllStudents();
        }

        @ResponseStatus
        @GetMapping("/email/pass")
        public Student getStudent(@PathVariable String email,@PathVariable String pass){
                return studentServices.getUser(email,pass);
        }
}
