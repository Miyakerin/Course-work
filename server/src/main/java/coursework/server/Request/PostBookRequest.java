package coursework.server.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostBookRequest {
    private String name;
    private String description;
    private String genre;
    private String author;
    private int ageRestriction;
    private String condition;
    private long loaner_id;
}
