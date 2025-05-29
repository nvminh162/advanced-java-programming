package dao;

import model.Product;

import java.util.List;

public interface ProductDAO extends GenericDAO<Product, Integer> {
    List<Product> listProductsWithTheHighestPriceSQL();

    List<Product> listProductsWithTheHighestPriceJPQL();

    List<Product> listProductsNotBeenSold1();

    List<Product> listProductsNotBeenSold2();
}
