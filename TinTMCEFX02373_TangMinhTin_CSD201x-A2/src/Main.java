
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
public class Main {

    // Show menu for user choice
    public int showMenu() {
        System.out.println("----------------- Book List -----------------");
        System.out.println("1. Input Book and add to the end");
        System.out.println("2. Display books");
        System.out.println("3. Search by code");
        System.out.println("4. Input Book and add to begining");
        System.out.println("5. Add Book after position k");
        System.out.println("6. Delete Book at position k");
        System.out.println("7. Sort the list of books as ascending by price");
        System.out.println("8. Save information of all books to text files");
        System.out.println("0. Exit");
        return new Validation().checkInt("Your choice");    // Check input of user choice
    }

    public static void main(String[] args) {
        // Create new object main and bookList
        Main main = new Main();
        BookList bookList = new BookList();
        try {
            while (true) {
                int choice = main.showMenu();   // Get choice
                switch (choice) {
                    case 1:
                        bookList.addLast(); // Add last
                        break;
                    case 2:
                        bookList.list();    // Show out list of book
                        break;
                    case 3:
                        bookList.search();  // Search
                        break;
                    case 4:
                        bookList.addFirst();    // Add first
                        break;
                    case 5:
                        bookList.addAfter();    // Add after position k
                        break;
                    case 6:
                        bookList.deleteAt();    // Add delete at position k
                        break;
                    case 7:
                        bookList.sortBooks();   // Sort list of book
                        break;
                    case 8:
                        bookList.saveBooks();   // Save books
                        break;
                    case 0: // Exit program
                        System.out.println("Thank for using main program!");
                        System.exit(0);
                    default:    // Print error
                        System.out.println("ERROR: Your must choice from 0 to 8");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
