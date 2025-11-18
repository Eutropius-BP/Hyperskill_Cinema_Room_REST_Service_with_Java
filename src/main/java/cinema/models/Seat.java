package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    protected int row;
    protected int col;

    protected boolean isReserved = false;
    protected boolean isPurchased = false;
    protected int price = 0;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Seat(int row, int col, int price) {
        this.row = row;
        this.col = col;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isReserved() {
        return isReserved;
    }

    @JsonIgnore
    public boolean isPurchased() {
        return isPurchased;
    }

    public int getPrice() {
        return price;
    }

    public void reserve() {
        isReserved = true;
    }

    public void purchase() {
        isPurchased = true;
    }

    public int getRow() {
        return row;
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public int getColumn() {
        return col;
    }
}
