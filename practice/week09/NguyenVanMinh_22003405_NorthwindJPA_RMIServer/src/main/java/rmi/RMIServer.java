package rmi;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderItemDAOImpl;
import dao.impl.ProductDAOImpl;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.Product;
import service.CustomerService;
import service.OrderItemService;
import service.OrderService;
import service.ProductService;
import service.impl.CustomerServiceImpl;
import service.impl.OrderItemServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(3405);

        CustomerDAO customerDAO = new CustomerDAOImpl(Customer.class);
        OrderDAO orderDAO = new OrderDAOImpl(Order.class);
        ProductDAO productDAO = new ProductDAOImpl(Product.class);
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl(OrderItem.class);

        CustomerService customerService = new CustomerServiceImpl(customerDAO); //Java Object
        OrderService orderService = new OrderServiceImpl(orderDAO);
        ProductService productService = new ProductServiceImpl(productDAO);
        OrderItemService orderItemService = new OrderItemServiceImpl(orderItemDAO);

        context.bind("rmi://paul:3405/customerService", customerService); //Java Remote Object
        context.bind("rmi://paul:3405/orderService", orderService);
        context.bind("rmi://paul:3405/productService", productService);
        context.bind("rmi://paul:3405/orderItemService", orderItemService);

        System.out.println("RMI Server is running ...");
    }
}
