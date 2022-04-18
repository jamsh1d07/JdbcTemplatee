package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Book {

    private Integer id ;
    private String name;
    private Double price;
    private Integer categoryId;
    private Integer authorId;

    public Book(String name, Double price, Integer categoryId, Integer authorId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }
}
