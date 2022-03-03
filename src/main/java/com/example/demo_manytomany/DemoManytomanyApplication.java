package com.example.demo_manytomany;

import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Course;
import com.example.demo_manytomany.model.Student;
import com.example.demo_manytomany.repository.BookRepository;
import com.example.demo_manytomany.repository.CourseRepository;
import com.example.demo_manytomany.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoManytomanyApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoManytomanyApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository, BookRepository bookRepository) {
        return args -> {
            Faker fk = new Faker();
//            for(int i=1;i<10;i++){
//                Student s=new Student();
//                s.setFirstName(fk.name().firstName());
//                s.setLastName(fk.name().lastName());
//                s.setEmail(fk.internet().emailAddress());
//                s.setAge(fk.number().numberBetween(18,70));
//                s.setPassword("123");
//                System.out.println(s);
//                studentRepository.save(s);
//            }
////
//                for(int i=1;i<10;i++){
//                    Course c=new Course();
//                    c.setName(fk.team().sport());
//                    c.setDepartment(fk.demographic().educationalAttainment());
//                    c.setDescription(fk.team().sport());
//                    c.setTime(2000);
//                    courseRepository.save(c);
//                }
            Course c=courseRepository.findById(2L).get();
            Student s=studentRepository.findById(4L).get();
//            c.addStudent(s);
            s.addCourse(c);

//            Book bk=bookRepository.findById(4L).get();
//            System.out.println("--------"+bk.getStudent());
//            s.removeBook(bk);
            studentRepository.save(s);


        };
    }
}
