package ru.burhanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.burhanov.exceptions.AuthorNotFoundException;
import ru.burhanov.models.Book;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().
                findAny().
                orElse(null);
    }

    public Optional<Book> show(String author) {
        return jdbcTemplate.query("select * from book where author=?", new BeanPropertyRowMapper<>(Book.class), author)
                .stream().
                findAny();
    }


    public void save(Book book) {
        jdbcTemplate.update("insert into book(name,author,year) values(?,?,?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update book set name=?, author=?,year=? where id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void remove(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }
}
