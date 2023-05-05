package coursework.server.repositories;

import coursework.server.models.Book;
import coursework.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * репозиторий созданный от таблицы book
 */
public interface BooksRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookById(long id);
    List<Book> findAllById(long id);
    List<Book> findAllByUser(User user);
}
