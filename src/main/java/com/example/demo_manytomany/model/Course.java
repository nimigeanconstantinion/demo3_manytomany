package com.example.demo_manytomany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Course")
@Table(name="course")
public class Course {
    public Course(String name, String department, double time, String description) {
        this.name = name;
        this.department = department;
        this.time = time;
        this.description = description;
    }

    @Id
    @SequenceGenerator(
            name = "course_sequance",
            sequenceName = "course_sequance",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequance"
    )
    private Long id;

    @Column(
            name = "name"
    )
    @NotBlank(message = "name is necessary")
    private String name;
    @Column(
            name = "department"
    )
    @NotBlank(message = "department is necessary")

    private String department;


    @NotNull(message = "time is necessary")
    private double time;

    @NotBlank(message = "description is necessary")
    private String description;

    @ManyToMany(mappedBy="courses",
    fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Student> students = new ArrayList<Student>();


    public void addStudent(Student student){

        this.students.add(student);
    }

    //add enrolment
    //remove enrolmment
    //getenrolments


    public Course(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        Course aux = (Course) o;
        return this.getName().equals(aux.getName()) && this.getDepartment().equals(aux.getDepartment()) && this.getDescription().equals(aux.getDescription());
    }

}
