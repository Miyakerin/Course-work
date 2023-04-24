package coursework.server.controllers;

import coursework.server.exceptions.BadRequestException;
import coursework.server.exceptions.NotFoundException;
import coursework.server.models.Book;
import coursework.server.models.User;
import coursework.server.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController { //to-do insertion, delete, sorting, search book by author e.t.c, add new loan by id
    @Autowired
    BooksRepository booksRepository;
    @GetMapping(value = "/get")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(booksRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value ="/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(value ="/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> postBook(@RequestBody Book newBook) {
        Book book = booksRepository.save(newBook);
        if (book == null) {
            throw new BadRequestException();
        } else {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/put/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book updBook) {
        Optional<Book> curBook = booksRepository.findById(id);
        if (curBook.isPresent()) {
            updBook.setId(id);
            booksRepository.save(updBook);
            return new ResponseEntity<>(updBook, HttpStatus.ACCEPTED);
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        Optional<Book> curBook = booksRepository.findById(id);
        if (curBook.isPresent()) {
            booksRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }
}
