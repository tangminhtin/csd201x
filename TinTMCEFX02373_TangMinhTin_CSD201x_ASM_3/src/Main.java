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

    private final Validation validation = new Validation();

    private int menu() {
        System.out.println("-----------------------------");
        System.out.println("Product list");
        System.out.println("1. Insert a new product");
        System.out.println("2. In-order traverse");
        System.out.println("3. Breath first traverse");
        System.out.println("4. Search by a product code");
        System.out.println("5. Delete by a product code");
        System.out.println("6. Simple balancing");
        System.out.println("7. Count number of products");
        System.out.println("8. Search product by price");
        System.out.println("9. Save all products to file");
        System.out.println("0. Exit");
        return validation.checkInt("Your choice");
    }

    public static void main(String[] args) {
        Main main = new Main();
        MyProduct product = new MyProduct();

        while (true) {
            int getMenu = main.menu();
            switch (getMenu) {
                case 1:
                    product.insert();
                    break;
                case 2:
                    product.inOrder();
                    break;
                case 3:
                    product.BFT();
                    break;
                case 4:
                    product.search();
                    break;
                case 5:
                    product.delete();
                    break;
                case 6:
                    product.balance();
                    break;
                case 7:
                    System.out.println("Number of products is: " + product.size());
                    break;
                case 8:
                    product.findPrice();
                    break;
                case 9:
                    product.saveProduct();
                    break;
                case 0:
                    System.out.println("Thank for using my application!");
                    System.exit(0);
                default:
                    System.out.println("ERROR: You must choice from 0 to 7!");
                    break;
            }
        }
    }
}
