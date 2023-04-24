package coursework.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "book_id")
    private long id;

    @Column(nullable = false, name = "book_name")
    private String name;

    @Column(nullable = false, name = "book_description")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    private User user;

    @Column(nullable = false, name = "book_genre")
    private String genre;

    @Column(nullable = false, name = "book_author")
    private String author;

    @Column(nullable = false, name = "book_age_restriction")
    private int ageRestriction;

    @Column(nullable = false, name = "book_condition")
    private String condition;


}
