package app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        final int MONTHS = 3;
        int[][] sales = new int[products.length][MONTHS];

        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < MONTHS; j++) {
                System.out.print("Enter number of sales for month " + (j + 1)
                        + " for " + products[i] + " mattress: ");
                sales[i][j] = input.nextInt();
            }
            System.out.println();
        }

        Product product = new Product(products, sales);
        PrintProduct printer = new PrintProduct(product);

        printer.PrintHeader();
        printer.PrintTotalSales();
        printer.PrintAverageSales();
        printer.PrintMostPopularProduct();

        input.close();
    }
}
