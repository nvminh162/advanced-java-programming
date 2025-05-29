package service;

import model.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProductService extends GenericService<Product, Integer> {
    List<Product> listProductsWithTheHighestPriceSQL() throws RemoteException;

    List<Product> listProductsWithTheHighestPriceJPQL() throws RemoteException;

    List<Product> listProductsNotBeenSold1() throws RemoteException;

    List<Product> listProductsNotBeenSold2() throws RemoteException;
}
