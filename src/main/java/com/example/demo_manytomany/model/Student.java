package com.example.demo_manytomany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Student")
@Table(
        name = "student"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "age",
            nullable = false

    )
    private Integer age;

    @Column(
            name="password",
            nullable = false
    )
    private String password;

    public Student(String firstName, String lastName, String email, String password,int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age=age;
    }

    @JsonManagedReference
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER

    )
    @JoinTable(name = "enrolment",
            joinColumns = { @JoinColumn(name = "fk_author") },
            inverseJoinColumns = { @JoinColumn(name = "fk_course") })
    private List<Course> courses = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    Set<Book> books = new HashSet<>();


    public void addCourse(Course course){
        this.courses.add(course);
    }

    @Transactional
    public void addBook(Book book){
        this.books.add(book);
        book.setStudent(this);
    }

    @Transactional
    public void removeBook(Book book){
        this.books.remove(book);

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        Student aux=(Student) o;

        return aux.getEmail().equals(this.getEmail())&&aux.getPassword().equals(aux.getPassword());
    }


}
