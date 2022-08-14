package engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException(long quizID, String username) {
        super(String.format("Error: %s cannot delete Quiz ID #%d,", username, quizID));
    }
}
