package cinema.models;

public class SeatResponse {
    private int row;
    private int column;
    private int price;

    public SeatResponse(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public SeatResponse(Seat seat) {
        this.row = seat.getRow();
        this.column = seat.getColumn();
        this.price = seat.getPrice();
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
