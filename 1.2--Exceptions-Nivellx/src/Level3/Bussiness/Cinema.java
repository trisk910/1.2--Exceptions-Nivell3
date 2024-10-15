package Level3.Bussiness;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {

    private int rows;
    private int seatsPerRow;
    private final SeatsManager seatsManager;
    private final CinemaManager cinemaManager;

    public Cinema() {
        this.seatsManager = new SeatsManager();
        this.cinemaManager = new CinemaManager();
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

        System.out.print("Introdueix el nombre de files: ");
        this.rows = scanner.nextInt();
        boolean errorRows1 = false;
        int rows = 0;
        while (!errorRows1) {
            try {
                System.out.print("Introdueix el nombre de seients per fila: ");
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
    }
    public void Start() {

    }
}
