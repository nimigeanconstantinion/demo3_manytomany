package com.example.demo_manytomany.services;

import com.example.demo_manytomany.exceptions.BadRequest;
import com.example.demo_manytomany.exceptions.BookException;
import com.example.demo_manytomany.exceptions.StundentExists;
import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Student;
import com.example.demo_manytomany.repository.BookRepository;
import com.example.demo_manytomany.repository.CourseRepository;
import com.example.demo_manytomany.repository.StudentRepository;

import java.util.stream.Collectors;

public class StudentServices {

        private StudentRepository studentRepository;
        private BookRepository bookRepository;
        private CourseRepository courseRepository;

        public StudentServices(StudentRepository studentRepository,BookRepository bookRepository,CourseRepository courseRepository){
            this.studentRepository=studentRepository;
            this.bookRepository=bookRepository;
            this.courseRepository=courseRepository;
        }

        public void addStudent(Student student){
            if(!studentRepository.findStudentByEmail(student.getEmail()).isPresent()){
                studentRepository.save(student);
            }else{
                throw new StundentExists("Student exists!!");
            }

        }

        public void addBook(Student student,Book book){
            if(!bookRepository.findBookByStudentAndTitle(student,book.getTitle()).isPresent()){
                student.addBook(book);
                studentRepository.save(student);
            }else{
                throw new BookException("You have this book");
            }
        }



}
