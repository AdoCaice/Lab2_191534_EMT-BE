package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Author author;
    Integer CopiesAvailable;

    public Book() {
    }

    public Book(String name, Category category, Author author, Integer copiesAvailable) {
        this.name = name;
        this.category = category;
        this.author = author;
        CopiesAvailable = copiesAvailable;
    }
}
