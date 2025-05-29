package Test;

import dao.impl.CustomerDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import model.Customer;
import model.Order;
import model.Product;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        JPAUtil.getEntityManager();

        /*System.out.println("Find the list of products with the highest price.");
        List<Product> getProductsWithHighestPrice = new ProductDAOImpl(Product.class).getProductsWithHighestPrice();
        getProductsWithHighestPrice.forEach(System.out::println);*/

        /*System.out.println("Find a list of products that have not been sold yet.");
        List<Product> productsWithoutSales = new ProductDAOImpl(Product.class).productsWithoutSales();
        productsWithoutSales.forEach(System.out::println);*/

        /*System.out.println("Statistics on the number of customers by each state.");
        System.out.println("Customer State | Count");
        Map<String, Long> getNumberCustomerByState = new CustomerDAOImpl(Customer.class).getNumberCustomerByState();
        getNumberCustomerByState.forEach((state, count) -> System.out.println(state + " | " + count));*/

        /*double calculateOrderTotalById = new OrderItemDAOImpl(OrderItem.class).calculateOrderTotalById(1);
        System.out.println(calculateOrderTotalById);*/

        /*Map<Customer, Long> getNumberOfOrdersForEachCustomer = new CustomerDAOImpl(Customer.class).getNumberOfOrdersForEachCustomer();
        getNumberOfOrdersForEachCustomer.forEach((k, v) -> System.out.println(k + " : " + v));*/

        /*Map<Product, Long> getTotalQuantityOfEachProductSold = new ProductDAOImpl(Product.class).getTotalQuantityOfEachProductSold();
        getTotalQuantityOfEachProductSold.forEach((k, v) -> System.out.println(k.getName() + " : " + v));*/

        /*double totalBillAmountForDay = new OrderDAOImpl(Order.class).totalBillAmountForDay(LocalDate.of(2016, 1, 3));
        System.out.println(totalBillAmountForDay);*/

        /*int deleteCustomersWithoutPurchases = new CustomerDAOImpl(Customer.class).deleteCustomersWithoutPurchases();
        System.out.println(deleteCustomersWithoutPurchases);*/

        Map<String, Integer> billsByMonth = new OrderDAOImpl(Order.class).totalBillsByMonth(Month.MARCH);
        System.out.println("Total bills by month (March):");
        billsByMonth.forEach((year, count) -> System.out.println("Year " + year + " : " + count));

        // Test tổng số hóa đơn theo năm (ví dụ: năm 2024)
        Map<String, Integer> billsByYear = new OrderDAOImpl(Order.class).totalBillsByYear(Year.of(2024));
        System.out.println("\nTotal bills by year (2024):");
        billsByYear.forEach((month, count) -> System.out.println("Month " + month + " : " + count));
    }
}
