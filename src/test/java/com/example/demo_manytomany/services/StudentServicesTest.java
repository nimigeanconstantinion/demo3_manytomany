package com.example.demo_manytomany.services;

import com.example.demo_manytomany.exceptions.StundentExists;
import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Student;
import com.example.demo_manytomany.repository.BookRepository;
import com.example.demo_manytomany.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class StudentServicesTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;
    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    private StudentServices underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        underTest=new StudentServices(this.studentRepository,this.bookRepository,null);
    }

    @Test
    void addStudentTestWork(){
        Faker fk=new Faker();
        Student s=new Student(fk.name().firstName(),
                fk.name().lastName(),
                fk.internet().emailAddress(),"123",33);
        //given

        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.empty());

        underTest.addStudent(s);
        then(studentRepository).should().save(studentArgumentCaptor.capture());

        Student newS=studentArgumentCaptor.getValue();

        assertThat(newS).isEqualTo(s);


    }

    @Test
    void addStudentTestNotWork(){
        Faker fk=new Faker();
        Student s=new Student(fk.name().firstName(),
                fk.name().lastName(),
                fk.internet().emailAddress(),"123",33);
        //given

        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.of(s));

        assertThatThrownBy(()->underTest.addStudent(s)).isInstanceOf(StundentExists.class).hasMessageContaining("Student exists");


    }


    @Test
    void addBookWork() {
        Faker fk=new Faker();
        Student student=studentRepository.findById(1L).get();
        Book book=new Book(fk.book().title());
        //given
        given(this.bookRepository.findBookByStudentAndTitle(student,book.getTitle())).willReturn(Optional.empty());

        underTest.addBook(student,book);
        //then
       then(studentRepository).should().save(student).addBook(bookArgumentCaptor.capture());
       Book bk=bookArgumentCaptor.getValue();
       assertThat(bk).isEqualTo(book);

    }
}