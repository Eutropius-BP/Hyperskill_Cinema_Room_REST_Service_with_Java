package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongToken extends RuntimeException{
    private static final String MESSAGE = "Wrong token!";

    public WrongToken() {
        super(MESSAGE);
    }
}
