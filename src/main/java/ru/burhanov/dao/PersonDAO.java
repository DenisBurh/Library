package ru.burhanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.burhanov.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {

        return jdbcTemplate.query("select * from person where id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().
                findAny().
                orElse(null);
    }
    public Optional<Person> show(String fullName) {

        return jdbcTemplate.query("select * from person where full_name=?", new BeanPropertyRowMapper<>(Person.class), fullName)
                .stream().
                findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(full_name,birth_year) values(?,?)",
                person.getFullName(),
                person.getBirthYear());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("update Person set full_name=?,birth_year=? where id=?",
                person.getFullName(),
                person.getBirthYear(),
                id);
    }

    public void remove(int id) {
        jdbcTemplate.update("delete from Person where id=?", id);
    }

}
