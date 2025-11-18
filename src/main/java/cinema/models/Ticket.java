package cinema.models;

import java.util.UUID;

public class Ticket {
    protected UUID token;
    protected Seat seat;

    public Ticket(Seat seat) {
        this.seat = seat;
        generateToken();
    }

    private void generateToken() {
        this.token = UUID.randomUUID();
    }

    public UUID getToken() {
        return token;
    }

    protected Ticket getTicket() {
        return this;
    }

    public Seat getSeat() {
        return seat;
    }
}
