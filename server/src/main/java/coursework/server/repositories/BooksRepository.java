package coursework.server.repositories;

import coursework.server.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookById(long id);
}
