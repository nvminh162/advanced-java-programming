package service;

import model.Order;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface OrderService extends GenericService<Order, Integer> {
    double getTotalAmount(LocalDate orderDate) throws RemoteException;
}
