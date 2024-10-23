package Level3.Bussiness;

import java.util.ArrayList;
import java.util.List;

public class SeatsManager {
    private List<Seat> reservedSeats;

    public SeatsManager() {
        this.reservedSeats = new ArrayList<>();
    }

    public void reserveSeat(Seat seat) {
        reservedSeats.add(seat);
    }

    public List<Seat> getReservedSeats() {
        return reservedSeats;
    }
}
