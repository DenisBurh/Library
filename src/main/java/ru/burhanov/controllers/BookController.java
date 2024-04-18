package ru.burhanov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.burhanov.dao.BookDAO;
import ru.burhanov.models.Book;
import ru.burhanov.util.BookValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {


    private final BookDAO bookDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookDAO bookDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";

    }

    @GetMapping("/new")
    public String createPage(Model model) {
        model.addAttribute("newBook", new Book());
        return "books/new";

    }

    @PostMapping()
    public String create(@ModelAttribute("newBook") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updatePage(Model model) {
        model.addAttribute("newBook", new Book());
        return "books/update";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("newBook") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/update";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.remove(id);
        return "redirect:/books";
    }


}
