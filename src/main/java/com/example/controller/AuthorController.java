package com.example.controller;

import com.example.ServiceJDBC.JDBCAuthor;
import com.example.entity.Author;
import com.example.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    JDBCAuthor jdbcAuthor;


    @PostMapping
    public HttpEntity<?> add(@RequestBody Author author) {
        try {
            jdbcAuthor.save(new Author(author.getName()));
            return new ResponseEntity<>("Author was created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Author author) {
//            author.setId(id);
        jdbcAuthor.update(author, id);
        return new ResponseEntity<>("Author was update successully.", HttpStatus.OK);
    }


    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jdbcAuthor.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Author authorById = jdbcAuthor.findById(id);
        if (authorById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(authorById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Author());
        }
    }
}
