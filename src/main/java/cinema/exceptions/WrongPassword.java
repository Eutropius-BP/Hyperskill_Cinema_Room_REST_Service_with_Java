package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class WrongPassword extends RuntimeException{
    private static final String MESSAGE = "The password is wrong!";
    public WrongPassword() {
        super(MESSAGE);
    }
}
