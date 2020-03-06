
import java.util.Scanner;
import util.MyList;

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

    // Check empty string
    public String checkEmpty(String msg) {
        while (true) {
            System.out.print(msg + ": ");
            String str = scanner.nextLine();
            if (!str.isEmpty()) {
                return str;
            } else {
                System.out.println("ERROR: " + msg + " cannot be empty!");
            }
        }
    }

    // Check integer number
    public int checkInt(String msg) {
        while (true) {
            System.out.print(msg + ": ");   // Print out message
            if (scanner.hasNextInt()) { // scanner has an integer number
                int num = scanner.nextInt();    // Get input from user
                scanner.nextLine();     // Remove enter key
                return num; // Return number of input
            } else {    // Print out error if invalid
                System.out.println("ERROR: " + msg + " must be a numeric!");
                scanner.next(); // Move the next of input
            }
        }
    }

    // Check double number
    public Double checkDouble(String msg) {
        while (true) {
            System.out.print(msg + ": ");   // Print out message
            if (scanner.hasNextDouble()) { // scanner has an integer number
                double num = scanner.nextDouble();    // Get input from user
                scanner.nextLine();     // Remove enter key
                return num; // Return number of input
            } else {    // Print out error if invalid
                System.out.println("ERROR: " + msg + " must be a numeric!");
                scanner.next(); // Move the next of input
            }
        }
    }

    // Check book is exist or not by book code
    public MyList isExisted(MyList books, String bCode) {
        for (int i = 0; i < books.size(); i++) {
            if (books.getNode(i).info.getbCode().equals(bCode)) {
                return books;
            }
        }
        return null;
    }

    // Check book code is duplicate or not
    public String checkDuplicateCode(MyList books) {
        while (true) {
            String bCode = checkEmpty("Book Code");
            if (isExisted(books, bCode) == null) {
                return bCode;
            } else {
                System.out.println("ERROR: Book Code cannot be duplicated");
            }
        }
    }
}
