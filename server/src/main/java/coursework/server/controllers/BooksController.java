package coursework.server.controllers;

import coursework.server.exceptions.BadRequestException;
import coursework.server.exceptions.NotFoundException;
import coursework.server.models.Book;
import coursework.server.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController { //to-do insertion, delete, sorting, search book by author e.t.c, add new loan by id
    @Autowired
    BooksRepository booksRepository;
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(booksRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(
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

    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updBook) {
        updBook.setId(id);
        booksRepository.save(updBook);
        return new ResponseEntity<>(updBook, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public void deleteBookById(@PathVariable long id) {
        booksRepository.deleteById(id);
    }
}
