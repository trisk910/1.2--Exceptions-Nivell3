package Level3.Bussiness;

import Level3.Persistance.CatchExceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CinemaManager {

    private Cinema cinema;

    public CinemaManager(){
        cinema = new Cinema();
    }
    public int menu() throws CatchExceptions{
        System.out.println("1.- Mostrar totes les butaques reservades.");
        System.out.println("2.- Mostrar les butaques reservades per una persona.");
        System.out.println("3.- Reservar una butaca.");
        System.out.println("4.- Anul·lar la reserva d’una butaca.");
        System.out.println("5.- Anul·lar totes les reserves d’una persona.");
        System.out.println("6. Sortir");

        Scanner scanner = new Scanner(System.in);
        boolean inputOk = false;
        int menuOption = 0;
        while (!inputOk) {
            try {
                System.out.print("Opcio: ");
                menuOption = scanner.nextInt();
                if (menuOption < 1 || menuOption > 6) {
                    System.out.println("Opcio menu no disponible\n");
                } else {
                    inputOk = true;
                }
            } catch (InputMismatchException e) {
                throw new CatchExceptions("Error de format: " + e.getMessage());
            }
        }

        return menuOption;
    }

    public void showAllSeats(){
        for (Seat seat : cinema.getSeatsManager().getReservedSeats()) {
            System.out.println(seat);
        }

    }

    public void showSeatPerson(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nom de la persona: ");
        String personName;
        personName = scanner.nextLine();

        boolean found = false;
        for (Seat seat : cinema.getSeatsManager().getReservedSeats()) {
            if (seat.getBookName().equals(personName)) {
                System.out.println(seat);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No s'han trobat butaques reservades per aquesta persona.");
        }
    }
    public void bookSeats(){
        try {
            int row = introRow();
            int seat = introSeat();
            String personName = introPerson();
            Seat newSeat = new Seat(row, seat, personName);
            cinema.getSeatsManager().reserveSeat(newSeat);
            System.out.println("Butaca reservada correctament.");
        } catch (CatchExceptions e) {
            System.out.println(e.getMessage());
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

    public void bookCancelation(){
        int row = introRow();
        int seat = introSeat();


    }
}
