/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class MyBSTree implements Serializable {

    private static final long serialVersionUID = 1L;

    //a root of tree
    Node<Product> root;

    public MyBSTree() {
        root = null;
    }

    // visit a node of a tree -> output information of visited node
    public void visit(Node<Product> p) {
        System.out.println(p.info.toString());
    }

    // return true if tree is empty otherwise return false
    public boolean isEmpty() {
        return root == null;
    }

    // inorder a tree
    public void inOrder() {
        if (isEmpty()) {     // If root is empty show message
            System.out.println("Your product list is empty!");
            return;
        }

        Stack<Node<Product>> stack = new Stack<>();
        Node<Product> current = root;   // Start at root

        // Traverse the tree
        System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price"));
        while (current != null || stack.size() > 0) {
            while (current != null) {
                stack.push(current);        // Push into stack
                current = current.left;     // Move to left side
            }

            current = stack.pop();      // Pop stack
            System.out.println(current.info.toString());
            current = current.right; // Move to right side
        }
    }

    // count number of products
    public int count() {
        return size(root);
    }

    // breadth-first traverse a tree
    public void BFT() {
        if (isEmpty()) {     // If root is empty show message
            System.out.println("Your product list is empty!");
            return;
        }

        MyQueue myQueue = new MyQueue();
        myQueue.enqueue(root);  // Start from root

        // traverse a tree
        System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price"));
        while (!myQueue.isEmpty()) {
            Node<Product> p = (Node<Product>) myQueue.dequeue();

            if (p.left != null) {
                myQueue.enqueue(p.left);    // Enqueue left child
            }

            if (p.right != null) {
                myQueue.enqueue(p.right);   // Enqueue right child
            }
            visit(p);   // output information of visited node
        }
    }

    // insert a new Product to a tree
    public void insert(Product product) {
        if (isEmpty()) { // If the root is empty, then add new Node at the root
            root = new Node<>(product);
            return;
        }

        Node<Product> p, f;
        p = root;   // Store parent Node
        f = null;   // Mark Node 

        while (p != null) {       // If Node is null, then stop
            if (p.info.getCode().equalsIgnoreCase(product.getCode())) {
                System.out.println("ERROR: Product code " + product.getCode() + " already exists, not insert!");
                return;
            }

            f = p;
            // If code of new product less then code in tree
            if (product.getCode().compareToIgnoreCase(p.info.getCode()) < 0) {
                p = p.left; // Move to left of tree
            } else {
                p = p.right; // Move to right of tree
            }
        }

        // If code of new product less then code in tree
        if (product.getCode().compareToIgnoreCase(f.info.getCode()) < 0) {
            f.left = new Node<>(product);   // Add left of tree
        } else {
            f.right = new Node<>(product);  // Add right of tree
        }
    }

    //balance a tree
    //step 1: traverse inorder tree and copy all item on tree to an arraylist
    //step 2: insert all items of list to a tree
    private void buildArray(List<Node<Product>> list, Node<Product> p) {
        if (p == null) {
            return;
        }
        buildArray(list, p.left);  // Move left tree
        list.add(p);               // Copy all item on tree to an arraylist
        buildArray(list, p.right); // Move right tree
    }

    //step 2:
    private void balance(List<Node<Product>> list, int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        Node<Product> p = list.get(mid); // Get middle of tree
        insert(p.info);                 // Store middle of tree
        visit(p);   // output information of visited node
        balance(list, start, mid - 1);  // Move left tree
        balance(list, mid + 1, end);    // Move right tree
    }

    public void balance() {
        List<Node<Product>> list = new ArrayList<>();
        buildArray(list, root);     // Copy root tree to arraylist
        MyBSTree tree = new MyBSTree();
        tree.balance(list, 0, list.size() - 1); // Peform balance
        root = tree.root;   // Store balance tree to root

        System.out.println("Your BST is balanced already!");
    }

    // search a Node of tree by product code
    // return null if given code does not exists
    public Node<Product> search(String code) {
        if (isEmpty()) { // If root is emtpy, then return null
            return null;
        }

        Node<Product> p = root; // Start at root
        while (p != null) {
            if (code.equalsIgnoreCase(p.info.getCode())) { // If search code equal code in tree
                return p;   // Then return p (node)
            }

            // If code of new product less then code in tree
            if (code.compareToIgnoreCase(p.info.getCode()) < 0) {
                p = p.left;     // Move to left of tree
            } else {
                p = p.right;    // Move to right of tree
            }
        }

        return p;
    }

    // delete a node by a given product code
    public void delete(String code) {
        Node<Product> product = this.search(code);

        if (product == null) {
            System.out.println("Product code '" + code + "' is not exist!");
        } else {
            root = deleteRecursive(root, code);
            System.out.println("Product code '" + code + "' has been deleted!");
            this.inOrder();
        }
    }

    // Delete a node and recursive to insert a new key in BST
    private Node<Product> deleteRecursive(Node<Product> root, String code) {
        if (root == null) { // If tree is empty
            System.out.println("Your product list is empty!");
            return root;
        }

        // Otherwise, recursive down the tree
        if (code.compareToIgnoreCase(root.info.getCode()) < 0) {
            root.left = deleteRecursive(root.left, code);
        } else if (code.compareToIgnoreCase(root.info.getCode()) > 0) {
            root.right = deleteRecursive(root.right, code);
        } else {
            // If key is same as root's key, then the node to be deleted 

            // Node with only one child or no child 
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children. Get the inorder successor (smallest in the right subtree) 
            root.info = minCode(root.right);

            // Delete the inorder successor 
            root.right = deleteRecursive(root.right, root.info.getCode());
        }
        return root;
    }

    //  Find smallest child of subtree
    private Product minCode(Node<Product> node) {
        Product min = node.info;
        while (node.left != null) {
            min = node.left.info;    // Find smallest left of subtree right
            node = node.left;
        }
        return min;
    }

    // Size
    private int size(Node<Product> node) {
        if (node == null) {
            return 0;
        } else {    // Use recursive to calculate
            return size(node.left) + 1 + size(node.right);
        }
    }

    // Find product by price
    public void findPrice(double price) {
        List<Node<Product>> list = new ArrayList<>();
        buildArray(list, root); // Copy node to array list

        if (list.isEmpty()) {
            System.err.println("Your product list is empty!");
            return;
        }
        List<Node<Product>> products = new ArrayList<>();

        for (Node<Product> p : list) {
            if (p.info.getPrice() >= price) { // Find price and add it
                products.add(p);
            }
        }

        if (!products.isEmpty()) {
            BSTPrice priceTree = new BSTPrice();
            priceTree.addTree(products);    // Store price to priceTree
            System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s", "Code", "Name", "Quantity", "Saled", "Price", "Balance Factor"));
            priceTree.inOrder(priceTree.root);  // Out information of price
        } else {
            System.out.println("Your price '" + price + "' not exist!");
        }
    }

}
