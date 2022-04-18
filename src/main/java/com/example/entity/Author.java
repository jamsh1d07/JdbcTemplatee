package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Author {

    private Integer id ;
    private  String name ;

    public Author(String name) {
        this.name = name;
    }
}
