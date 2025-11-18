package cinema.models;

public class TicketInfo {
    private int row;
    private int column;
    private int price;

    public TicketInfo(Ticket ticket) {
        this.row = ticket.getSeat().getRow();
        this.column = ticket.getSeat().getColumn();;
        this.price = ticket.getSeat().getPrice();;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
