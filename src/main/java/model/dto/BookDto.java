package model.dto;

import lombok.Data;
import model.Category;

@Data
public class BookDto {

    private String name;

    private Category category;

    Long author;

    Integer copiesAvailable;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, Integer copiesAvailable) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }
}