package com.example.demo_manytomany.repository;

import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.email=?1")
    Optional<Student> findStudentByEmail(String email);


}
