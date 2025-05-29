package dao;

import model.Product;

import java.util.List;
import java.util.Map;

public interface ProductDAO extends GenericDAO<Product, Integer> {
    //  Find the list of products with the highest price.
    List<Product> getProductsWithHighestPrice();

    //  Find a list of products that have not been sold yet.
    List<Product> productsWithoutSales();

    //  Calculate the total quantity of each product sold.
    Map<Product, Long> getTotalQuantityOfEachProductSold();
}
