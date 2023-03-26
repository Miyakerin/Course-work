package coursework.server.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "account")
public class User {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    @Nonnull
    private int authorityLevel;
    @Nonnull
    private int age;
}
