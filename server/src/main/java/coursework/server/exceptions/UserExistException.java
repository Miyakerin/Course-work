package coursework.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ошибка, сообщабщая о том, что пользователь уже сущесвует с кодом 400
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistException extends RuntimeException{
}
