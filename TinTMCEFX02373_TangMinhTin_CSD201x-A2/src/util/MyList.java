/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Book;
import java.io.Serializable;

/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class MyList implements Serializable {

    Node<Book> head, tail, sorted;

    //ctor
    public MyList() {
        head = tail = null;
    }

    //check if the list is empty or not
    public boolean isEmpty() {
        return head == null;
    }

    //add a new Book to the end of list
    public void addLast(Book b) {
        Node<Book> newNode = new Node<>(b);
        if (head == null) {  // If the node is empty
            head = newNode; // Then newNode become a head
            return;
        }

        newNode.next = null;  // Last node(newNode) will point to null
        tail = head;          // Tail beginning at head
        while (tail.next != null) {
            tail = tail.next; // Move tail to last node
        }

        tail.next = newNode;  // Last node become tail
    }

    //add a new Book to the begining of list
    public void addFirst(Book b) {
        Node<Book> newNode = new Node<>(b); // Put object book into Node
        newNode.next = head;        // Next node become a head
        head = newNode;        // Head become a newNode
    }

    //output information of all books in the list
    public void traverse() {
        Node<Book> current = head;
        if (current == null) {
            System.out.println("The book list is empty!");
            return;
        }
        // SHow all books 
        System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s\n", "Code", "Title", "Quantity", "Lender", "Price", "Value");
        while (current != null) {
            System.out.println(current.info.toString());
            current = current.next;
        }
    }

    //return number of nodes/elements in the list
    public int size() {
        int length = 0;
        Node<Book> current = head;

        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    //return a Node at position k, starting position is 0
    public Node<Book> getNode(int k) {
        Node<Book> current = head;
        int pos = 0;
        while (current != null) {
            if (pos == k) {     // If position equal to k 
                return current; // Then return current
            }
            pos++;
            current = current.next;
        }
        return null;
    }

    //add a new book after a position k
    public void addAfter(Book b, int k) {
        Node<Book> leftNode = getNode(k);   // Get the left node of the position of new node

        if (leftNode == null) {  // Check left node is null, then print out error
            System.out.println("ERROR: Can't add new book after position '" + k + "'");
            return;
        }

        Node<Book> newNode = new Node<>(b); // Create new node of book
        newNode.next = leftNode.next;   // Make the next of new node point to the next of the left node 
        leftNode.next = newNode;    // Make the next of the left node point to new node
        System.out.println("A new book has been added after position '" + k + "'");
    }

    //delete a book at position k
    public void deleteAt(int k) {

        if (k < 0) {    // Invalid positon
            System.out.println("ERROR: Position '" + k + "' is invalid!");
            return;
        }

        if (head == null) { // if link list is empty, then return
            System.out.println("ERROR: The list of books is empty");
            return;
        }

        Node<Book> leftNode = head;    // leftNode(previous) beginning at head of Node

        if (k == 0) {        // if delete at head of Node(first element)
            head = leftNode.next;   // the head of node become the next node of leftNode
            System.out.println("The book with code '" + leftNode.info.getbCode() + "' at position " + k + " is deleted");
            return;
        }

        // Find the leftNode(previous) of the node to be delete 
        for (int i = 0; i < k - 1 && leftNode != null; i++) {
            leftNode = leftNode.next;
        }

        // if position is greater than the number of node, then print over size
        if (leftNode == null || leftNode.next == null) {
            System.out.println("ERROR: Position '" + k + "' is over size of the list of book");
            return;
        }

        System.out.println("The book with code '" + leftNode.next.info.getbCode() + "' at position '" + k + "' is deleted");
        Node rightNode = leftNode.next.next; // Store pointer the right of the node to be deleted
        leftNode.next = rightNode; // Unlink the deleted node from list (next of leftNode become rightNode)
    }

    //search a Node by a given book code
    public Node<Book> search(String bCode) {
        Node<Book> current = head;  // Store current node

        while (current != null) {
            // If book code in link list equal to book code of user then return current
            if (current.info.getbCode().toLowerCase().contains(bCode.toLowerCase())) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    // Sort the list of books as ascending by price 
    public void insertionSort() {
        sorted = null;      // Init for sorted link list
        Node<Book> current = head;  // Store current node

        while (current != null) {
            Node<Book> nextNode = current.next; // Store the next node for next iteration
            sorted(current);    // Insert current in stored link links
            current = nextNode; // Update current
        }
        head = sorted;      // Update head point to stored link list
    }

    // Insert a new_node in a list
    public void sorted(Node<Book> newNode) {
        // Special case for the head end
        if ((sorted == null) || (sorted.info.getPrice() >= newNode.info.getPrice())) {
            newNode.next = sorted;
            sorted = newNode;
        } else {
            Node<Book> current = sorted;
            // Locate the node before the point of insertion
            while ((current.next != null) && (current.next.info.getPrice() < newNode.info.getPrice())) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

    }

}
