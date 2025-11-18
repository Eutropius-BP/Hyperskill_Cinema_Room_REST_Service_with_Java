package cinema.models;

import java.util.UUID;

public class TicketResponse {
    private UUID token;
    private TicketInfo ticket;

    public TicketResponse(Ticket ticket) {
        this.token = ticket.getToken();
        this.ticket = new TicketInfo(ticket);
    }


    public UUID getToken() {
        return token;
    }

    public TicketInfo getTicket() {
        return ticket;
    }

}
