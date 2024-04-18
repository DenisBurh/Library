package ru.burhanov.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Component
public class Book {
    private int id;

    @NotEmpty(message = "Name of book should not be empty")
    @Size(min = 3, max = 100, message = "Too short or too long name")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 3, max = 100, message = "Too short or too long characters for author")
    private String author;

    @Positive(message = "Year should be positive number")
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

}
