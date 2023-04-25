package coursework.server.controllers;

import coursework.server.Request.PostBookRequest;
import coursework.server.Service.BookService;
import coursework.server.Response.BookResponse;
import coursework.server.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService service;
    @GetMapping(value = "/get")
    public ResponseEntity<List<Book>> getAllBooks() {
        return service.getAll();
    }

    @GetMapping(value ="/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @PostMapping(value ="/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> postBook(
            @RequestBody PostBookRequest request) {
        return ResponseEntity.ok(service.post(request));
    }

    @PutMapping(value = "/put/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") long id, @RequestBody PostBookRequest request) {
        return ResponseEntity.ok(service.put(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
