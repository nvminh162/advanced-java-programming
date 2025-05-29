package service.impl;

import dao.ProductDAO;
import model.Product;
import service.ProductService;

import java.rmi.RemoteException;
import java.util.List;

public class ProductServiceImpl extends GenericServiceImpl<Product, Integer> implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) throws RemoteException {
        super(productDAO);
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> listProductsWithTheHighestPriceSQL() throws RemoteException {
        return productDAO.listProductsWithTheHighestPriceSQL();
    }

    @Override
    public List<Product> listProductsWithTheHighestPriceJPQL() throws RemoteException {
        return productDAO.listProductsWithTheHighestPriceJPQL();
    }

    @Override
    public List<Product> listProductsNotBeenSold1() throws RemoteException {
        return productDAO.listProductsNotBeenSold1();
    }

    @Override
    public List<Product> listProductsNotBeenSold2() throws RemoteException {
        return productDAO.listProductsNotBeenSold2();
    }
}
