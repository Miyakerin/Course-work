package coursework.server.Service;

import coursework.server.Request.PostBookRequest;
import coursework.server.Response.BookResponse;
import coursework.server.models.Book;
import coursework.server.repositories.BooksRepository;
import coursework.server.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksRepository booksRepository;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    public BookResponse post(PostBookRequest request) {
        if(usersRepository.findById(request.getLoaner_id()).isEmpty()) {
            return BookResponse.builder().success(false).build();
        }
        var book = Book.builder()
                .ageRestriction(request.getAgeRestriction())
                .author(request.getAuthor())
                .condition(request.getCondition())
                .description(request.getDescription())
                .genre(request.getGenre())
                .name(request.getName())
                .user(usersRepository.findById(request.getLoaner_id()).get())
                .build();
        booksRepository.save(book);
        return BookResponse.builder().success(true).build();
    }

    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(booksRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Book> getById(long id) {
        Optional<Book> book = booksRepository.findBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public BookResponse put(PostBookRequest request, long id) {
        if (usersRepository.findById(request.getLoaner_id()).isEmpty()) {
            return BookResponse.builder().success(false).build();
        }
        if (booksRepository.findBookById(id).isEmpty()) {
            return BookResponse.builder().success(false).build();
        }
        var book = Book.builder()
                .id(id)
                .user(usersRepository.findById(request.getLoaner_id()).get())
                .name(request.getName())
                .description(request.getName())
                .author(request.getAuthor())
                .condition(request.getCondition())
                .ageRestriction(request.getAgeRestriction())
                .genre(request.getGenre())
                .build();
        booksRepository.save(book);
        return BookResponse.builder().success(true).build();
    }

    public BookResponse deleteById(long id) {
        if (booksRepository.findBookById(id).isEmpty()) {
            return BookResponse.builder().success(false).build();
        }
        booksRepository.deleteById(id);
        return BookResponse.builder().success(true).build();
    }

    public ResponseEntity<List<Book>> getAvailable() {
         return new ResponseEntity<>(booksRepository.findAllByUser(usersRepository.findById((long)-1000).get()).stream().toList(), HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getBooksByToken(String token) {
        if (token == null || !token.startsWith("Bearer ")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String jwtToken = token.substring(7);
        String userEmail = jwtService.extractUserEmail(jwtToken);
        if (usersRepository.findByEmail(userEmail).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booksRepository.findAllByUser(usersRepository.findByEmail(userEmail).get()).stream().toList(), HttpStatus.OK);
    }
}
