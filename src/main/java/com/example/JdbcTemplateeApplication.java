package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@SpringBootApplication
public class JdbcTemplateeApplication {

    @Value("${spring.sql.init.mode}")
    static String mode;

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateeApplication.class, args);

        if (mode == ("always")) {

            SingleConnectionDataSource ds = new SingleConnectionDataSource();
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setUrl("jdbc:postgresql://localhost:5432/jdbcbook");
            ds.setUsername("postgres");
            ds.setPassword("rot1234");


            JdbcTemplate jt = new JdbcTemplate(ds);
            jt.execute("create table category ( id serial primary key ,\n" +
                    "    name varchar NOT NULL)");
            jt.execute("insert into category(id,name ) values (1,'nimadir')");

            jt.execute("create table author( id serial primary key ,\n" +
                    "    name varchar NOT NULL )");
            jt.execute("insert into author(id , name) values (1,'Utkir Hoshimov')");

            jt.execute("create table book (id serial PRIMARY KEY ,\n" +
                    "name varchar(50) NOT NULL,\n" +
                    " price double precision NOT NULL,\n" +
                    " author_id int NOT NULL,\n" +
                    " category_id int NOT NULL,\n" +
                    " FOREIGN KEY (category_id)  REFERENCES category(id),\n" +
                    " FOREIGN KEY (author_id) REFERENCES  author(id) )");
            jt.execute("insert into book (id, name,price,category_id,author_id) values (1, 'A',100,1,1)");

            ds.destroy();
        }
    }
}
