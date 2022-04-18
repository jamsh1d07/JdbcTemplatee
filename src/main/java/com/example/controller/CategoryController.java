package com.example.controller;

import com.example.ServiceJDBC.JDBCCategory;
import com.example.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    JDBCCategory jdbcCategory;

    @PostMapping
    public HttpEntity<?> add(@RequestBody Category category) {
        try {
            jdbcCategory.save(new Category(category.getName()));
            return new ResponseEntity<>("Category was created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Category category) {
//            category.setId(id);
        jdbcCategory.update(category, id);
        return new ResponseEntity<>("Category was update successully.", HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jdbcCategory.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Category categoryById = jdbcCategory.findById(id);
        if (categoryById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category());
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        Category categoryById = jdbcCategory.findById(id);
        if (categoryById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(jdbcCategory.deleteById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category());
        }
    }

}
