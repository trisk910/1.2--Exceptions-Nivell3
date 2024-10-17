package Level3.Bussiness;

import Level3.Persistance.*;

import java.util.*;

public class CinemaManager {

    private Cinema cinema;
    private static String seatMessage = "No hi ha cap butaca reservada.\n";
    private static String personMessage = "No s'ha trobat cap reserva amb aquest nom.\n";
    private static String allSeatsOccupied = "No s'ha trobat cap reserva per aquesta butaca.\n";
    private static String wrongRow = "Fila no disponible.\n";
    private static String wrongSeat = "Seient no disponible.\n";
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

    public void showAllSeats() throws ExceptionEmptyList {
        List<Seat> reservedSeats = cinema.getSeatsManager().getReservedSeats();
        if (reservedSeats.isEmpty()) {
            throw new ExceptionEmptyList(seatMessage);
        } else {
            for (Seat seat : reservedSeats) {
                System.out.println(seat.toString());
            }
            System.out.println();
        }

    }

    public void showSeatPerson() throws ExceptionEmptyList, ExceptionPersonNotFound {
        if(cinema.getSeatsManager().getReservedSeats().isEmpty()){
            throw new ExceptionEmptyList(seatMessage);
        }else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdueix el nom de la persona: ");
            String personName;
            personName = scanner.nextLine();
            try {
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
                    throw new ExceptionPersonNotFound(personMessage);
                }
            }catch (ExceptionPersonNotFound e){
                System.out.println(e.getMessage());
            }

        }
    }
    public void bookSeats() throws ExceptionAllSeatsOccupied, ExceptionWrongRow, ExceptionSeatOccupied {
        boolean booked = false;
        while (!booked) {
            try {
                if (cinema.getSeatsManager().getReservedSeats().size() == cinema.getRows() * cinema.getSeatsPerRow()) {
                    throw new ExceptionAllSeatsOccupied(allSeatsOccupied);
                }else{
                    boolean errorRow = false;
                    int row = 0;
                    while (!errorRow) {
                        try {
                            row = introRow();
                            if (row < 1 || row > cinema.getRows()) {
                                throw new ExceptionWrongRow(wrongRow);
                            }
                            errorRow = true;
                        } catch (ExceptionWrongRow e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    boolean errorSeat = false;
                    int seat = 0;
                    while (!errorSeat) {
                        try {
                            seat = introSeat();
                            if (seat < 1 || seat > cinema.getSeatsPerRow()) {
                                throw new ExceptionSeatOccupied(wrongSeat);
                            }
                            errorSeat = true;
                        } catch (ExceptionSeatOccupied e) {
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
                        throw new ExceptionSeatOccupied(wrongSeat);
                    } else {
                        String personName = introPerson();
                        Seat newSeat = new Seat(row, seat, personName);
                        cinema.getSeatsManager().reserveSeat(newSeat);
                        System.out.println("Butaca reservada correctament.\n");
                        booked = true;
                    }
                }
            } catch (ExceptionAllSeatsOccupied e) {
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

    public void bookCancelation() throws ExceptionEmptyList{
        if(cinema.getSeatsManager().getReservedSeats().isEmpty()){
            throw new ExceptionEmptyList(seatMessage);
        }else {
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
    }

    public void cancelAllSeats() throws ExceptionEmptyList, ExceptionPersonNotFound {
        if(cinema.getSeatsManager().getReservedSeats().isEmpty()){
            throw new ExceptionEmptyList(seatMessage);
        }else {
            String personName = introPerson();
            boolean removed = false;
            List<Seat> reservedSeats = cinema.getSeatsManager().getReservedSeats();
            try {
                for (int i = 0; i < reservedSeats.size(); i++) {
                    if (reservedSeats.get(i).getBookName().equals(personName)) {
                        reservedSeats.remove(i);
                        i--;
                        removed = true;
                    }
                }
                if (removed)
                    System.out.println("Totes les reserves de " + personName + " han estat eliminades.\n");
                else
                    throw new ExceptionPersonNotFound(personMessage);
            } catch (ExceptionPersonNotFound e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
