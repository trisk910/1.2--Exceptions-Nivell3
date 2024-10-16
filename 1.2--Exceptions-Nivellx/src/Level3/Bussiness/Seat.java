package Level3.Bussiness;

public class Seat {
    private final int row;
    private final int seat;
    private final String bookName;

    public Seat(int row, int seat, String bookName) {
        this.row = row;
        this.seat = seat;
        this.bookName = bookName;
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
        if (this == obj)
            return true;
        if (!(obj instanceof Seat seat))
            return false;
        return row == seat.row && this.seat == seat.seat;
    }

    @Override
    public String toString() {
        return "Fila: " + row + ", Seient: " + seat + ", Persona: " + bookName;
    }
}
