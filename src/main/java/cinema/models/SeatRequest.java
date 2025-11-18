package cinema.models;

import jakarta.validation.constraints.Min;

public class SeatRequest {
    @Min(value = 1, message = "Row must be at least 1")
    private int row;

    @Min(value = 1, message = "Column must be at least 1")
    private int column;

    public SeatRequest() {}

    public SeatRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


}
