package com.example.demo_manytomany;

import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Course;
import com.example.demo_manytomany.model.Student;
import com.example.demo_manytomany.repository.BookRepository;
import com.example.demo_manytomany.repository.CourseRepository;
import com.example.demo_manytomany.repository.StudentRepository;
import com.example.demo_manytomany.services.StudentServices;
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
    CommandLineRunner commandLineRunner(StudentServices studentServices) {
        return args -> {
            Faker fk = new Faker();
            System.out.println(studentServices.getAllStudents());
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
//          adaugare enrolment
//            Course c=courseRepository.findById(7L).get();
//            Student s=studentRepository.findById(4L).get();
//
//            s.addCourse(c);
//            studentRepository.save(s);

//adaugare book
//
//                Student s=studentRepository.findById(4L).get();
//                Book bk=new Book("Cartea noua");
//                s.addBook(bk);
//                studentRepository.save(s);

//        Book bk=bookRepository.findById(4L).get();
//        Student s=studentRepository.findById(3L).get();
//        s.removeBook(bk);
//        studentRepository.save(s);
//  delete enrolment
//                Student s=studentRepository.findById(4L).get();
//                Course c=courseRepository.findById(7L).get();
//                s.removeCourse(c);
//                studentRepository.save(s);

//        Student s=studentRepository.findById(4L).get();
//            //System.out.println(s.getBooks().toString());
//            //System.out.println(s.getCourses().toString());
//            System.out.println("Student :"+s.getFirstName()+" "+s.getLastName());
//            System.out.println("Cartile mele :");
//            s.getBooks().forEach((e)->{
//                System.out.println(e.getTitle());
//            });
//            System.out.println("Cursurile mele :");
//            s.getCourses().forEach((c)->{
//                System.out.println(c.getName()+" "+c.getDepartment()+" dep:"+c.getDepartment());
//            });


        };
    }
}
