package coursework.server.Request;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private String role;
}
