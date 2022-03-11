package com.example.demo_manytomany.services;

import com.example.demo_manytomany.exceptions.BookException;
import com.example.demo_manytomany.exceptions.NotFoundBook;
import com.example.demo_manytomany.exceptions.StundentExists;
import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Course;
import com.example.demo_manytomany.model.Student;
import com.example.demo_manytomany.repository.BookRepository;
import com.example.demo_manytomany.repository.CourseRepository;
import com.example.demo_manytomany.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class StudentServicesTest {

    Faker fk=new Faker();
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CourseRepository courseRepository;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;
    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;
    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;


    @Autowired
    private StudentServices underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        underTest=new StudentServices(this.studentRepository,this.bookRepository,this.courseRepository);
    }

    @Test
    void addStudentTestWork(){

        Student s=new Student(fk.name().firstName(),
                fk.name().lastName(),
                fk.internet().emailAddress(),"123",33,0);
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
                fk.internet().emailAddress(),"123",33,0);
        //given
        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.of(s));

        assertThatThrownBy(()->underTest.addStudent(s)).isInstanceOf(StundentExists.class).hasMessageContaining("Student exists");

    }


    @Test
    void addBookTestWork() {
        Faker fk=new Faker();
        Student s=new Student(fk.name().firstName(),
                fk.name().lastName(),
                fk.internet().emailAddress(),"123",33,0);
        Book book=new Book(fk.book().title());
        //given
        given(this.bookRepository.findBookByStudentAndTitle(s,book.getTitle())).willReturn(Optional.empty());

        underTest.addBook(s,book);

       then(studentRepository).should().save(studentArgumentCaptor.capture());
       Book bk=studentArgumentCaptor.getValue().getBooks().get(0);
       assertThat(bk).isEqualTo(book);

    }

    @Test
    void addBookTestNotWork() {
        Faker fk=new Faker();
        Student s=new Student(fk.name().firstName(),
                fk.name().lastName(),
                fk.internet().emailAddress(),"123",33,0);

        Book book=new Book(fk.book().title());

        //given
        given(this.bookRepository.findBookByStudentAndTitle(s,book.getTitle())).willReturn(Optional.of(book));

        assertThatThrownBy(()->underTest.addBook(s,book)).isInstanceOf(BookException.class).hasMessageContaining("this");

    }


    @Test
    void addEnrolmentWork() {
        Course c= new Course("Cursul 1","IT",2D,"descript");
        Student s=new Student("Ion","Ion","jj@yahoo.com","123",20,0);
        //given
        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.of(s));
        given(this.courseRepository.findById(c.getId())).willReturn(Optional.of(c));
        underTest.addEnrolment(s,c);
        then(studentRepository).should().save(studentArgumentCaptor.capture());
        Course cn = studentArgumentCaptor.getValue().getCourses().get(0);
        cn.addStudent(s);
        Student st=cn.getStudents().get(0);
        assertThat(c).isEqualTo(cn);
        assertThat(st).isEqualTo(s);
    }

    @Test
    void addEnrolmentNotFindStudent() {
        Course c= new Course("Cursul 1","IT",2D,"descript");
        Student s=new Student("Ion","Ion","jj@yahoo.com","123",20,0);
        //given
        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.addEnrolment(s,c)).hasMessageContaining("Student not");
    }

    @Test
    void addEnrolmentNotFindCourse() {
        Course c= new Course("Cursul 1","IT",2D,"descript");
        Student s=new Student("Ion","Ion","jj@yahoo.com","123",20,0);
        //given
        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.of(s));

        given(this.courseRepository.findById(c.getId())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.addEnrolment(s,c)).hasMessageContaining("Course didn't");
    }



    @Test
    void removeEnrolment() {
        Course c= new Course("Cursul 1","IT",2D,"descript");
        Student s=new Student("Ion","Ion","jj@yahoo.com","123",20,0);
        //given
        given(this.studentRepository.findStudentByEmail(s.getEmail())).willReturn(Optional.of(s));
        given(this.courseRepository.findById(c.getId())).willReturn(Optional.of(c));

        underTest.removeEnrolment(s,c);
        then(studentRepository).should().save(studentArgumentCaptor.capture());
        Student sn=studentArgumentCaptor.getValue();
        assertThat(sn).isEqualTo(s);
    }

    @Test
    void addCourse() {
    }

    @Test
    void removeCourse() {
    }
}