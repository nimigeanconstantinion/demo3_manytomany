package com.example.demo_manytomany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name="Book")
@Table(name="book")
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;

    @Column(
            name="title",
            columnDefinition = "TEXT"
    )
    String title;

    public Book(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Book> getBooks(){
        return this.getBooks();
    }

    @ManyToOne
    @JoinColumn(
            name="student_id",
            referencedColumnName = "id"
    )
    @JsonBackReference
    Student student;

    public boolean equals(Object o){
        Book b=(Book) o;
        return this.title.equals(b.title);
    }
}
