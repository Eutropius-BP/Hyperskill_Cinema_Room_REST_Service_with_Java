package cinema.services;

import cinema.exceptions.TicketAlreadyPurchased;
import cinema.models.Cinema;
import cinema.models.Seat;
import cinema.models.StatResponse;
import cinema.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CinemaService {
    final static int totalRows = 9;
    final static int totalCols = 9;
    public Cinema cinema;
    private final ConcurrentHashMap<String, ReentrantLock> seatLocks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Ticket> tickets = new ConcurrentHashMap<>();

    public CinemaService() {
        this.cinema = new Cinema(totalRows,totalCols);
        initialzeSeatLocks();
    }

    private void initialzeSeatLocks() {
        for (int i = 0; i <= totalRows; i++) {
            for (int j = 0; j <= totalCols; j++) {
                seatLocks.put(getSeatKey(i+1, j+1), new ReentrantLock());
            }
        }
    }

    private String getSeatKey(int row, int col) {
        return row + "-" + col;
    }

    public Seat getSeat(int row, int col) {
        return cinema.getSeat(row, col);
    }

    public Ticket purchaseTicket(Seat seat) {
        String key = getSeatKey(seat.getRow(), seat.getColumn());
        ReentrantLock lock = seatLocks.get(key);

        lock.lock();
        try {
            if (seat.isPurchased()) {
                throw new TicketAlreadyPurchased();
            }
            seat.purchase();
            cinema.setPurchased(cinema.getPurchased() + 1);
            cinema.setAvailable(cinema.getAvailable() - 1);
            cinema.setIncome(cinema.getIncome() + seat.getPrice());
            Ticket ticket = new Ticket(seat);
            System.out.println("Ticket purchased, token: " + ticket.getToken() + " ");
            tickets.put(ticket.getToken().toString(), ticket);
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public Ticket getTicketByToken(String token) {
        return tickets.get(token);
    }

    public Ticket getTicketByToken(UUID token) {
        return tickets.get(token.toString());
    }

    public boolean returnTicket(String token) {
        Ticket ticket = tickets.remove(token);
        if (ticket != null) {
            // Optionally unpurchase the seat if you want to allow returns
             ticket.getSeat().setPurchased(false);
             cinema.setAvailable(cinema.getAvailable() + 1);
             cinema.setPurchased(cinema.getPurchased() - 1);
             cinema.setIncome(cinema.getIncome() - ticket.getSeat().getPrice());
            return true;
        }
        return false;
    }

    public StatResponse getStats(){
        return new StatResponse(cinema.getIncome(), cinema.getAvailable(), cinema.getPurchased());
    }

}
