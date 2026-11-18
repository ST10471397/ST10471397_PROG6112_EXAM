package app;

public class Product implements IProduct {

    private final String[] names;   // single-dimensional array: product names
    private final int[][] sales;    // two-dimensional array: [product][month]

    public Product(String[] names, int[][] sales) {
        this.names = names;
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
        }
        return total;
    }

    // Average number sold per product per month: total / (products x months)
    @Override
    public double GetAverageProductSold() {
        int entries = names.length * sales[0].length;
        if (entries == 0) {
            return 0;
        }
        return (double) GetTotalProductsSold() / entries;
    }

    @Override
    public String GetMostPopularProduct() {
        return names[GetMostPopularIndex()];
    }

    // Total sales over all months for the most popular product
    public int GetMostPopularProductTotal() {
        return GetProductTotal(GetMostPopularIndex());
    }

    // Best single month for the most popular product (the sample report shows this figure)
    public int GetMostPopularProductPeak() {
        int index = GetMostPopularIndex();
        int peak = sales[index][0];
        for (int j = 1; j < sales[index].length; j++) {
            if (sales[index][j] > peak) {
                peak = sales[index][j];
            }
        }
        return peak;
    }

    private int GetProductTotal(int index) {
        int total = 0;
        for (int j = 0; j < sales[index].length; j++) {
            total += sales[index][j];
        }
        return total;
    }

    private int GetMostPopularIndex() {
        int bestIndex = 0;
        int bestTotal = GetProductTotal(0);
        for (int i = 1; i < sales.length; i++) {
            int total = GetProductTotal(i);
            if (total > bestTotal) {
                bestTotal = total;
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}
