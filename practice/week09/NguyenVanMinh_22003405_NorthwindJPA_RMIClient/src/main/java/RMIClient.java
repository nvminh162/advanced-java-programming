import model.Customer;
import model.Order;
import model.OrderItem;
import model.Product;
import service.CustomerService;
import service.OrderItemService;
import service.OrderService;
import service.ProductService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        CustomerService customerService = (CustomerService) context.lookup("rmi://paul:3405/customerService");
        OrderService orderService = (OrderService) context.lookup("rmi://paul:3405/orderService");
        ProductService productService = (ProductService) context.lookup("rmi://paul:3405/productService");
        OrderItemService orderItemService = (OrderItemService) context.lookup("rmi://paul:3405/orderItemService");

        //Customer
        Customer customer = customerService.findById(1);
        System.out.println(customer);
        System.out.println("************************************************");

        //Order
        double getTotalAmount = orderService.getTotalAmount(LocalDate.of(2016, 1, 1));
        System.out.println(getTotalAmount);
        System.out.println("************************************************");

        //Product
        List<Product> listProductsWithTheHighestPriceSQL = productService.listProductsWithTheHighestPriceSQL();
        List<Product> listProductsWithTheHighestPriceJPQL = productService.listProductsWithTheHighestPriceJPQL();
        List<Product> listProductsNotBeenSold1 = productService.listProductsNotBeenSold1();
        List<Product> listProductsNotBeenSold2 = productService.listProductsNotBeenSold2();

        listProductsWithTheHighestPriceSQL.forEach(System.out::println);
        System.out.println("************************************************");
        listProductsWithTheHighestPriceJPQL.forEach(System.out::println);
        System.out.println("************************************************");
        listProductsNotBeenSold1.forEach(System.out::println);
        System.out.println("************************************************");
        listProductsNotBeenSold2.forEach(System.out::println);
        System.out.println("************************************************");

        //OrderItem
        Product product = productService.findById(4);
        Order order = orderService.findById(1);
        OrderItem orderItem = orderItemService.findById(new OrderItem.OrderItemId(order, product));
        System.out.println(product + " | " + order + " | " + orderItem);
        System.out.println("************************************************");
    }
}
