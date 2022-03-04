package com.example.demo_manytomany.repository;

import com.example.demo_manytomany.model.Book;
import com.example.demo_manytomany.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("select b from Book b where b.student=?1 and b.title=?2")
    Optional<Book> findBookByStudentAndTitle(Student student,String title);
}
