
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class Validation {

    private final Scanner scanner = new Scanner(System.in);

    // Check double number
    public Double checkPrice(String msg) {
        while (true) {
            System.out.print(msg + ": ");   // Print out message
            if (scanner.hasNextDouble()) { // scanner has an integer number
                double num = scanner.nextDouble();    // Get input from user
                scanner.nextLine();     // Remove enter key
                if (num >= 0) {
                    return num; // Return number of input
                }
            } else {    // Print out error if invalid
                System.out.println("ERROR: " + msg + " must be a numeric!");
                scanner.next(); // Move the next of input
            }
        }
    }

    // Check Int
    public int checkInt(String msg) {
        while (true) {
            System.out.print(msg + ": ");   // Print out message
            if (scanner.hasNextInt()) { // scanner has an integer number
                int num = scanner.nextInt();    // Get input from user
                scanner.nextLine();     // Remove enter key
                if (num >= 0) {
                    return num; // Return number of input
                }
            } else {    // Print out error if invalid
                System.out.println("ERROR: " + msg + " must be a numeric!");
                scanner.next(); // Move the next of input
            }
        }
    }

    // Check empty string
    public String checkEmpty(String msg) {
        while (true) {
            System.out.print(msg + ": ");
            String str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                return str;
            } else {
                System.out.println("ERROR: " + msg + " cannot be empty!");
            }
        }
    }

}
