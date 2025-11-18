package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectRowOrColumn extends RuntimeException{

    private static final String MESSAGE = "The number of a row or a column is out of bounds!";

    public IncorrectRowOrColumn() {
        super(MESSAGE);
    }
}
