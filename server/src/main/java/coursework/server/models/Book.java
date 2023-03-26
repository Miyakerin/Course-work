package coursework.server.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Book {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Nonnull
    private String name;
    @Nonnull
    private String description;
    @Nonnull
    private long loanerId;
    @Nonnull
    private String genre;
    @Nonnull
    private String author;
    @Nonnull
    private int ageRestriction;
    @Nonnull
    private String condition;
}
