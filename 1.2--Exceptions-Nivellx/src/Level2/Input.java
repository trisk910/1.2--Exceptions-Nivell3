package Level2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static byte readByte() {
        while (true) {
            try {
                System.out.print("Introdueix un byte: ");
                return scanner.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
    }

    public static int readInt() {
        while (true) {
            try {
                System.out.print("Introdueix un int: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
    }

    public static float readFloat() {
        while (true) {
            try {
                System.out.print("Introdueix un float: ");
                return scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                System.out.print("Introdueix un double: ");
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error de format");
                scanner.next();
            }
        }
    }

    public static char readChar() {
        while (true) {
            try {
                System.out.print("Introdueix un char: ");
                String input = scanner.next();
                if (input.length() != 1) {
                    throw new Exception("Error de format");
                }
                return input.charAt(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String readString() {
        while (true) {
            try {
                System.out.print("Introdueix un string: ");
                return scanner.next();
            } catch (Exception e) {
                System.out.println("Error de format");
            }
        }
    }

    public static boolean readSN() {
        while (true) {
            try {
                System.out.print("Introdueix 's' o 'n': ");
                String input = scanner.next().toLowerCase();
                if (input.equals("s")) {
                    return true;
                } else if (input.equals("n")) {
                    return false;
                } else {
                    throw new Exception("Error de format");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
