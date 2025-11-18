package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class Cinema {

    protected int totalRows;
    protected int totalCols;
    protected int totalSeats;
    protected List<Seat> seats = new ArrayList<>();

    @JsonIgnore
    protected int income = 0;
    @JsonIgnore
    protected int available = 0;
    @JsonIgnore
    protected int purchased = 0;

    public Cinema(int rows, int cols) {
        this.totalRows = rows;
        this.totalCols = cols;
        this.totalSeats = rows * cols;
        this.available = totalSeats;
        this.initSeats();
    }

    private void initSeats(){
        for (int i = 0; i < totalRows; i++) {
            int price = i < 4 ? 10 : 8;
            for (int j = 0; j < totalCols; j++) {
                seats.add(new Seat(i+1, j+1, price));
            }
        }
    }



    public int getRows() {
        return totalRows;
    }

    public int getColumns() {
        return totalCols;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(int row, int col) {
        return seats.stream().filter(seat -> seat.getRow() == row && seat.getColumn() == col).findFirst().orElse(null);
    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

}
