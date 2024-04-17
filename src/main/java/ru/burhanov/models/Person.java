package ru.burhanov.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Component
public class Person {

    private int id;
    @Pattern(regexp = "", message = "Format : Динар Фатхел Баянович")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 100, message = "Too short or too long name")
    private String fullName;

    @NotEmpty(message = "Year should not be empty")
    @Positive(message = "Year should be positive number")
    private int birthYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {
    }
}
