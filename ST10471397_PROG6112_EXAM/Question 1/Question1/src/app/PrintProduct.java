package app;

public class PrintProduct {

    private final Product product;

    public PrintProduct(Product product) {
        this.product = product;
    }

    public void PrintHeader() {
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("*******************************************");
    }

    public void PrintTotalSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageSales() {
        System.out.println("AVERAGE PRODUCT SALES: "
                + String.format("%.0f", product.GetAverageProductSold()));
    }

    public void PrintMostPopularProduct() {
        // The sample output shows 25 for Cozy Nights Monarch (its best month).
        // To show its 3-month total (63) instead, use GetMostPopularProductTotal().
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct()
                + "(" + product.GetMostPopularProductPeak() + ")");
    }
}
