/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Book;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import util.MyList;
import util.Node;

/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class BookList {

    private Validation validation = new Validation();
    private static String FILEPATH = "src/bookData.txt";

    //a list of book
    private MyList books;

    public MyList getBooks() {
        return books;
    }

    public BookList() {
        books = this.loadBooks();   // Load book from text file
        if (books == null) {
            books = new MyList();

//            books.addLast(new Book("B03", "Morning", 12, 0, 25.1));
//            books.addLast(new Book("B01", "The noon", 10, 0, 5.2));
//            books.addLast(new Book("B02", "The river", 5, 0, 4.3));
//            books.addLast(new Book("B05", "Physics", 7, 0, 15.4));
//            books.addLast(new Book("B07", "Biology", 11, 0, 12.2));
//            books.addLast(new Book("B04", "Southern", 9, 0, 5.2));
        }
    }

    //1.0 accept information of a Book
    public Book getBook() {
        System.out.println("Enter information of Book");
        String bCode = validation.checkDuplicateCode(books);
        String title = validation.checkEmpty("Book Title");
        int quantity = validation.checkQuantity("Book Quantity");
        int lended = validation.checkLended("Book Lended", quantity);
        double price = validation.checkPrice("Book Price");
        // Return book after get information from user
        return new Book(bCode, title, quantity, lended, price);
    }

    //1.1 accept and add a new Book to the end of book list
    public void addLast() {
        books.addLast(this.getBook());
        System.out.println("A new book has been added to the end of list");
    }

    //1.2 output information of book list
    public void list() {
        books.traverse();
    }

    //1.3 search book by book code
    public void search() {
        String bCode = validation.checkEmpty("Enter book code");
        Node<Book> foundBooks = books.search(bCode);
        if (foundBooks == null) {
            System.out.println("The book list is empty!");
        } else { // Show information of book after search
            System.out.println("Information of book code " + bCode);
            System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s\n", "Code", "Title", "Quantity", "Lender", "Price", "Value");
            System.out.println(foundBooks.info.toString());
        }

    }

    //1.4 accept and add a new Book to the begining of book list
    public void addFirst() {
        books.addFirst(this.getBook());
        System.out.println("A new book has been added to the beginning of list");

    }

    //1.5 Add a new Book after a position k
    public void addAfter() {
        Book b = this.getBook();
        int k = validation.checkInt("Enter adding position");
        books.addAfter(b, k);
    }

    //1.6 Delete a Book at position k
    public void deleteAt() {
        int k = validation.checkInt("Enter deleting position");
        books.deleteAt(k);
    }

    // Sort book asending by price
    public void sortBooks() {
//            books.insertionSort();
        books.sort();
        books.traverse();
    }

    // Save books to text file
    public void saveBooks() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILEPATH));
            oos.writeObject(books); // Write object to file
            oos.close();
            System.out.println("Save information of all books was successful!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Load books from text file
    public MyList loadBooks() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH));
            MyList bookList = (MyList) ois.readObject();  // Read objet from file
            ois.close();
            System.out.println("Load information of all books was successful!");
            return bookList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
