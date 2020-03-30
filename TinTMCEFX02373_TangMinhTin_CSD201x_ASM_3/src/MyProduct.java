/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Product;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import util.MyBSTree;
import util.Node;

/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class MyProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Validation validation = new Validation();
    private static final String FILEPATH = "src/productData.txt";

    //a list of products
    MyBSTree tree;

    public MyProduct() {
        tree = this.loadProduct();

        if (tree == null) {
            tree = new MyBSTree();
            tree.insert(new Product("P04", "CD", 10, 2, 0.7));
            tree.insert(new Product("P01", "DCD", 7, 8, 1.2));
            tree.insert(new Product("P05", "Book", 21, 9, 2.9));
            tree.insert(new Product("P02", "Tape", 11, 16, 0.3));
        }
    }

    // 1.1 input and insert a new product to tree
    public void insert() {
        System.out.println("---> Insert New Product <---");
        String code = "";
        boolean exist = false;

        while (!exist) { // Check duplication product
            code = validation.checkEmpty("Code of product");
            if (tree.search(code) == null) {
                break;
            } else {
                System.out.println("ERROR: Product code cannot duplicate!");
            }
        }
        String name = validation.checkEmpty("Name of product");
        int quantity = validation.checkInt("Quantity");
        int saled = validation.checkInt("Saled");
        double price = validation.checkPrice("Price");

        tree.insert(new Product(code, name, quantity, saled, price));   // Insert product
        System.out.println("New product added success!");
    }

    // 1.2 in-order traverse
    public void inOrder() {
        tree.inOrder();
    }

    // 1.3 BFT a tree
    public void BFT() {
        tree.BFT();
    }

    // 1.4 search a product by product code
    public void search() {
        String code = validation.checkEmpty("Enter product code to search");
        Node<Product> found = tree.search(code);

        if (found == null) { // If product not exist, sthen how error
            System.out.println("Product code '" + code + "' doesn't exist.");
        } else {
            System.out.println("Information of product code '" + code + "'");
            tree.visit(found);  // Show information of product
        }

    }

    // 1.5 delete a product by product code
    public void delete() {
        String code = validation.checkEmpty("Enter product code to delete");
        tree.delete(code);
    }

    // 1.6 simply balancing a tree
    public void balance() {
        tree.balance();
    }

    // 1.7 count the number of products in the tree
    public int size() {
        return tree.count();
    }

    // Save product to text file
    public void saveProduct() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILEPATH));
            oos.writeObject(tree); // Write object to file
            oos.close();
            System.out.println("Save information of all products was successful!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Load product from text file
    private MyBSTree loadProduct() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH));
            MyBSTree product = (MyBSTree) ois.readObject();  // Read objet from file
            ois.close();
            System.out.println("Load information of all products was successful!");
            return product;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Find product by price
    public void findPrice() {
        double price = validation.checkPrice("Enter price to find");
        tree.findPrice(price);
    }

}
