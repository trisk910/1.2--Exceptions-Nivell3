package Level3.Bussiness;

import Level3.Persistance.CatchExceptions;

import java.util.*;

public class CinemaManager {

    private Cinema cinema;
    private static String seatMessage = "No hi ha cap butaca reservada.\n";
    public CinemaManager(Cinema cinema) {
        this.cinema = cinema;
    }
    public int menu() throws CatchExceptions{
        Scanner scanner = new Scanner(System.in);
        int menuOption = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("1.- Mostrar totes les butaques reservades.");
            System.out.println("2.- Mostrar les butaques reservades per una persona.");
            System.out.println("3.- Reservar una butaca.");
            System.out.println("4.- Anul·lar la reserva d’una butaca.");
            System.out.println("5.- Anul·lar totes les reserves d’una persona.");
            System.out.println("6. Sortir");

            System.out.print("Opcio: ");
            String input = scanner.nextLine();
            try {
                menuOption = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Selecciona una opció del menu amb un número.\n");
            }
        }
        System.out.println();
        return menuOption;
    }

    public void showAllSeats(){
        List<Seat> reservedSeats = cinema.getSeatsManager().getReservedSeats();
        if (reservedSeats.isEmpty()) {
            System.out.println("No hi ha cap butaca reservada.\n");
        } else {
            for (Seat seat : reservedSeats) {
                System.out.println(seat.toString());
            }
            System.out.println();
        }

    }

    public void showSeatPerson(){
        if(cinema.getSeatsManager().getReservedSeats().isEmpty()){
            System.out.println(seatMessage);
        }else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdueix el nom de la persona: ");
            String personName;
            personName = scanner.nextLine();

            boolean found = false;
            List<Seat> reservedSeats = cinema.getSeatsManager().getReservedSeats();
            for (int i = 0; i < reservedSeats.size(); i++) {
                Seat seat = reservedSeats.get(i);
                if (seat.getBookName().equals(personName)) {
                    System.out.println(seat);
                    found = true;
                    i = reservedSeats.size();
                }
            }

            if (!found) {
                System.out.println("No s'han trobat butaques reservades per aquesta persona.\n");
            }
        }
    }
    public void bookSeats(){
        boolean booked = false;
        while (!booked) {
            try {
                if (cinema.getSeatsManager().getReservedSeats().size() == cinema.getRows() * cinema.getSeatsPerRow()) {
                    throw new CatchExceptions("No queden butaques disponibles.");
                }else{
                    boolean errorRow = false;
                    int row = 0;
                    while (!errorRow) {
                        try {
                            row = introRow();
                            if (row < 1 || row > cinema.getRows()) {
                                throw new CatchExceptions("Fila no disponible.");
                            }
                            errorRow = true;
                        } catch (CatchExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    boolean errorSeat = false;
                    int seat = 0;
                    while (!errorSeat) {
                        try {
                            seat = introSeat();
                            if (seat < 1 || seat > cinema.getSeatsPerRow()) {
                                throw new CatchExceptions("Seient no disponible.");
                            }
                            errorSeat = true;
                        } catch (CatchExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    boolean isReserved = false;
                    List<Seat> reservedSeats = cinema.getSeatsManager().getReservedSeats();
                    for (int i = 0; i < reservedSeats.size(); i++) {
                        Seat reservedSeat = reservedSeats.get(i);
                        if (reservedSeat.getRow() == row && reservedSeat.getSeat() == seat) {
                            isReserved = true;
                            i = reservedSeats.size();
                        }
                    }
                    if (isReserved) {
                        System.out.println("Aquesta butaca ja esta ocupada.");
                    } else {
                        String personName = introPerson();
                        Seat newSeat = new Seat(row, seat, personName);
                        cinema.getSeatsManager().reserveSeat(newSeat);
                        System.out.println("Butaca reservada correctament.\n");
                        booked = true;
                    }
                }
            } catch (CatchExceptions e) {
                System.out.println(e.getMessage());
            }
        }


    }
    private int introRow() throws CatchExceptions{
        Scanner scanner = new Scanner(System.in);
        int row = 0;
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print("Introdueix el número de fila: ");
                row = scanner.nextInt();
                ok = true;
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
        return row;
    }
    private int introSeat() throws CatchExceptions{
        Scanner scanner = new Scanner(System.in);
        int seat = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Introdueix el número de seient: ");
                seat = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
        return seat;
    }
    private String introPerson(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nom de la persona: ");
        return scanner.nextLine();
    }

    public void bookCancelation(){/////////////////////////////////////////
        int row = introRow();
        int seat = introSeat();
        Seat seatToRemove = new Seat(row, seat, null);
        boolean removed = cinema.getSeatsManager().getReservedSeats().remove(seatToRemove);
        if (removed) {
            System.out.println("Reserva de la butaca eliminada correctament.\n");
        } else {
            System.out.println("No s'ha trobat cap reserva per aquesta butaca.\n");
        }
    }

    public void cancelAllSeats() {
        if(cinema.getSeatsManager().getReservedSeats().isEmpty()){
            System.out.println("No hi ha cap butaca reservada.\n");
        }else {
            String personName = introPerson();
            boolean removed = false;
            List<Seat> reservedSeats = cinema.getSeatsManager().getReservedSeats();
            for (int i = 0; i < reservedSeats.size(); i++) {
                if (reservedSeats.get(i).getBookName().equals(personName)) {
                    reservedSeats.remove(i);
                    i--;
                    removed = true;
                }
            }
            if (removed) {
                System.out.println("Totes les reserves de " + personName + " han estat eliminades.\n");
            } else {
                System.out.println("No s'ha trobat cap reserva per aquesta persona.\n");
            }

        }
    }
}
