package coursework.server.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ответ сервера, содержащий информацию о статусе результата выполнения ф-ции
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private boolean success;
}
