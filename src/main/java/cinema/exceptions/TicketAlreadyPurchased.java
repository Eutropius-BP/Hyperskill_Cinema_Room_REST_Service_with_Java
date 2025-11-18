package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TicketAlreadyPurchased extends RuntimeException{

    private static final String MESSAGE = "The ticket has been already purchased!";

    public TicketAlreadyPurchased() {
        super(MESSAGE);
    }
}
