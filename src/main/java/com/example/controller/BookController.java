package com.example.controller;

import com.example.ServiceJDBC.JDBCBook;
import com.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    JDBCBook jdbcBook;

    @PostMapping
    public HttpEntity<?> add(@RequestBody Book book) {
        try {
            jdbcBook.save(new Book(book.getName(),book.getPrice(), book.getCategoryId(), book.getAuthorId()));
            return new ResponseEntity<>("Book was created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Book book) {
//            book.setId(id);
        jdbcBook.update(book, id);
        return new ResponseEntity<>("Book was update successully.", HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jdbcBook.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Book bookById = jdbcBook.findById(id);
        if (bookById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bookById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Book());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        Book bookById = jdbcBook.findById(id);
        if (bookById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(jdbcBook.deletById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Book());
        }
    }
}
