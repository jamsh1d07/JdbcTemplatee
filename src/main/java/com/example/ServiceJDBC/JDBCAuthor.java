package com.example.ServiceJDBC;

import com.example.entity.Author;
import com.example.entity.Category;
import com.example.repository.AuthorRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JDBCAuthor implements AuthorRepository {
    JdbcTemplate jdbcTemplate;

    @Override
    public int save(Author author) {
        return jdbcTemplate.update("INSERT into author(name ) values (?)",
                new Object[] {author.getName()});

    }

    @Override
    public int update(Author author, Integer id) {
        return jdbcTemplate.update("Update author SET name =? WHERE id = ?",
                author.getName(),id);
    }

    @Override
    public Author findById(Integer id) {
        Author author = jdbcTemplate.queryForObject("SELECT * FROM author WHERE id=?",
                BeanPropertyRowMapper.newInstance(Author.class), id);
        return author;
    }



    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query("SELECT * FROM author",BeanPropertyRowMapper.newInstance(Author.class));
    }


    //Real proyectda author o'chirilmaydi faqat qo'shilishi o'zgartirilishi mumkin !!!
}
