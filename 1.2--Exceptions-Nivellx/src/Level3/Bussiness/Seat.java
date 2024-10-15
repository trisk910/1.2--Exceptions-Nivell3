package Level3.Bussiness;

public class Seat {
    private int row;
    private int seat;
    private String bookName;

    public Seat(int fila, int seient, String nomReserva) {
        this.row = fila;
        this.seat = seient;
        this.bookName = nomReserva;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Seat)) return false;
        Seat seat = (Seat) obj;
        return row == seat.row && this.seat == seat.seat;
    }

    @Override
    public String toString() {
        return "Fila: " + row + ", Seient: " + seat + ", Persona: " + bookName;
    }
}
