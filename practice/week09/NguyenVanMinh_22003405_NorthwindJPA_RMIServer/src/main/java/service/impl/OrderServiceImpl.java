package service.impl;

import dao.OrderDAO;
import model.Order;
import service.OrderService;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class OrderServiceImpl extends GenericServiceImpl<Order, Integer> implements OrderService {
    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) throws RemoteException {
        super(orderDAO);
        this.orderDAO = orderDAO;
    }

    @Override
    public double getTotalAmount(LocalDate orderDate) throws RemoteException {
        return orderDAO.getTotalAmount(orderDate);
    }
}
