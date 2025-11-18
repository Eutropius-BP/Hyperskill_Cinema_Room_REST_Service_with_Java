package cinema.models;

public class ReturnTicketResponse {
    private TicketInfo ticket;

    public ReturnTicketResponse(Ticket ticket) {
        this.ticket = new TicketInfo(ticket);
    }

    public TicketInfo getTicket() {
        return ticket;
    }
}
