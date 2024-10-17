package Level3.Bussiness;

import Level3.Persistance.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {

    private int rows;
    private int seatsPerRow;
    private final SeatsManager seatsManager;
    private final CinemaManager cinemaManager;

    public Cinema() {
        this.seatsManager = new SeatsManager();
        this.cinemaManager = new CinemaManager(this);
        createCinema();
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public SeatsManager getSeatsManager() {
        return seatsManager;
    }

    public CinemaManager getCinemaManager() {
        return cinemaManager;
    }

    private void createCinema(){
        Scanner scanner = new Scanner(System.in);
        boolean errorRows1 = false;
        int rows = 0;
        while (!errorRows1) {
            try {
                System.out.print("Introdueix el nombre de files: ");
                rows = scanner.nextInt();
                errorRows1 = true;
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
        this.rows = rows;

        boolean errorRows2 = false;
        int seatsPerRow2 = 0;
        while (!errorRows2) {
            try {
                System.out.print("Introdueix el nombre de seients per fila: ");
                seatsPerRow2 = scanner.nextInt();
                errorRows2 = true;
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
        this.seatsPerRow = seatsPerRow2;
        System.out.println();
    }
    public void start() throws ExceptionAllSeatsOccupied, ExceptionWrongRow, ExceptionSeatOccupied, ExceptionEmptyList{
        int option;
        do {
            option = cinemaManager.menu();
            switch (option) {
                case 1:
                    try
                    {
                        cinemaManager.showAllSeats();
                    }
                    catch (ExceptionEmptyList e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try
                    {
                        cinemaManager.showSeatPerson();
                    }
                    catch (ExceptionEmptyList e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try
                    {
                        cinemaManager.bookSeats();
                    }
                    catch (ExceptionAllSeatsOccupied | ExceptionWrongRow | ExceptionSeatOccupied e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        cinemaManager.bookCancelation();
                    } catch (ExceptionEmptyList e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        cinemaManager.cancelAllSeats();
                    } catch (ExceptionEmptyList e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        } while (option != 6);
    }
}
