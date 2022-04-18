package com.example.ServiceJDBC;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JDBCCategory implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
//    @Autowired
//    JDBCCategory jdbcCategory;

    @Override
    public int save(Category category) {
       return jdbcTemplate.update("INSERT into category(name ) values (?)",
                new Object[] {category.getName()});
    }

    @Override
    public int update(Category category,Integer id) {
        return jdbcTemplate.update("Update category SET name =? WHERE id = ?",
                category.getName(),id);
    }
//    @Override
//    public int update(Category category) {
//        return jdbcTemplate.update("Update category SET name =? WHERE id = ?",
//                category.getName(),category.getId());
//    }

    @Override
    public Category findById(Integer id) {
        Category category = jdbcTemplate.queryForObject("SELECT * FROM category WHERE id=?",
                BeanPropertyRowMapper.newInstance(Category.class), id);
        return category;
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM category WHERE id=?",id);
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM category",BeanPropertyRowMapper.newInstance(Category.class));

    }

    @Override
    public int deleteAll() {
       return jdbcTemplate.update("DELETE FROM category");
    }
}
