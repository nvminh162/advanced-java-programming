package service.impl;

import dao.OrderItemDAO;
import model.OrderItem;
import service.OrderItemService;

import java.rmi.RemoteException;

public class OrderItemServiceImpl extends GenericServiceImpl<OrderItem, OrderItem.OrderItemId> implements OrderItemService {
    private OrderItemDAO orderItemDAO;

    public OrderItemServiceImpl(OrderItemDAO orderItemDAO) throws RemoteException {
        super(orderItemDAO);
        this.orderItemDAO = orderItemDAO;
    }
}
