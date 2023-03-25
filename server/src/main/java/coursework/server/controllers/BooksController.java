package coursework.server.controllers;

import coursework.server.models.Book;
import coursework.server.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static java.util.Objects.nonNull;

@Controller
public class BooksController { //to-do insertion, delete, sorting, search book by author e.t.c, add new loan by id
    @Autowired
    BooksRepository booksRepository;
    @GetMapping("/books")
    public String getBooksPage(Model model) {
        List<Book> books = booksRepository.findAll();
        model.addAttribute("books", books);
        return "books_page";
    }
}
