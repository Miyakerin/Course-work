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
@RequestMapping("/api")
@RequiredArgsConstructor
public class BooksController {

    private final BookService service;
    @GetMapping(value = "employee/books/get")
    public ResponseEntity<List<Book>> getAllBooks() {
        return service.getAll();
    }

    @GetMapping(value ="employee/books/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @GetMapping(value = "user/available/get")
    public ResponseEntity<List<Book>> getAvailableBooks(){
        return service.getAvailable();
    }

    @GetMapping(value = "user/me/loans")
    public ResponseEntity<List<Book>> getBooksByToken(@RequestHeader(value = "Authorization") String authHeader){
        return service.getBooksByToken(authHeader);
    }

    @GetMapping(value = "employee/books/getloan/{id}")
    public ResponseEntity<List<Book>> getLoansByUserId(@PathVariable("id") long id) {
        return service.getLoanByUserId(id);
    }

    @PostMapping(value ="employee/books/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> postBook(
            @RequestBody PostBookRequest request) {
        return ResponseEntity.ok(service.post(request));
    }

    @PutMapping(value = "employee/books/put/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") long id, @RequestBody PostBookRequest request) {
        return ResponseEntity.ok(service.put(request, id));
    }

    @DeleteMapping("employee/books/delete/{id}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
