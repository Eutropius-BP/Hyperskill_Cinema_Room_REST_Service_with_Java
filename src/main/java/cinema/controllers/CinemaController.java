package cinema.controllers;

import cinema.exceptions.IncorrectRowOrColumn;
import cinema.exceptions.TicketAlreadyPurchased;
import cinema.exceptions.WrongPassword;
import cinema.exceptions.WrongToken;
import cinema.models.*;

import cinema.services.CinemaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getSeats() {
        return  cinemaService.cinema;
    }

    @PostMapping("/purchase")
    public TicketResponse purchase( @RequestBody SeatRequest seatRequest) {
        int row = seatRequest.getRow();
        int col = seatRequest.getColumn();

        Seat seat = cinemaService.getSeat(row, col);

        if (seat == null) {
            throw new IncorrectRowOrColumn();
        }
        Ticket ticket = cinemaService.purchaseTicket(seat);
        return new TicketResponse(ticket);


    }

    @PostMapping("/return")
    public ReturnTicketResponse returnTicket(@Valid @RequestBody TokenRequest tokenRequest) {
        String token = tokenRequest.getToken();

        Ticket ticket = cinemaService.getTicketByToken(token);

        if (ticket == null) {
            throw new WrongToken();
        }
        if (cinemaService.returnTicket(token)) {
            return new ReturnTicketResponse(ticket);
        }
        throw new WrongToken();
    }

    @GetMapping("/stats")
    public StatResponse getStat(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            throw new WrongPassword();
        }
        return cinemaService.getStats();
    }
}
