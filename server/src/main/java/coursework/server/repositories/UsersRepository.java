package coursework.server.repositories;

import coursework.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * репозиторий созданный от таблицы account
 */
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    long countAllByEmail(String email);
}
