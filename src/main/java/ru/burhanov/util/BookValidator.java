package ru.burhanov.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.burhanov.dao.BookDAO;
import ru.burhanov.dao.PersonDAO;
import ru.burhanov.exceptions.AuthorNotFoundException;
import ru.burhanov.models.Book;
import ru.burhanov.models.Person;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
// Книгу нельзя создать без человека в таблице Person
        String temp = book.getAuthor();
        if (personDAO.index()
                .stream()
                .filter(x -> x.getFullName().equals(temp))
                .findAny()
                .orElse(null) == null) {
            errors.rejectValue("author", "", "This author is not founded");
        }


    }
}
