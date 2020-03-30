/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Product;
import java.util.List;

/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class BSTPrice {

    // A root of tree
    Node<Product> root;

    public BSTPrice() {
        root = null;
    }

    // insert a tree
    public void insert(Product product) {
        root = insertRecursive(root, product);
    }

    // insert use recursive
    private Node<Product> insertRecursive(Node<Product> root, Product product) {
        if (root == null) {
            root = new Node<>(product);
            return root;
        }
        if (product.getPrice() < root.info.getPrice()) {
            root.left = insertRecursive(root.left, product);
        } else if (product.getPrice() > root.info.getPrice()) {
            root.right = insertRecursive(root.right, product);
        }
        return root;
    }

    // inorder a tree
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.info.toString() + String.format("%-10s", this.balanceFactor(root)));
            inOrder(root.right);
        }
    }

    // add tree
    public void addTree(List<Node<Product>> list) {
        for (int i = 0; i < list.size(); i++) {
            insert(list.get(i).info);
        }
    }

    // get height of tree
    public int balanceFactor(Node<Product> root) {
        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(balanceFactor(root.left), balanceFactor(root.right));
        }
    }
}
