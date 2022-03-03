package com.example.demo_manytomany.repository;

import com.example.demo_manytomany.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
