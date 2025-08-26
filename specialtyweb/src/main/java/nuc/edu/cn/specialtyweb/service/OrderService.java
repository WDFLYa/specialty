package nuc.edu.cn.specialtyweb.service;

import nuc.edu.cn.specialtyweb.entity.Order;
import nuc.edu.cn.specialtyweb.entity.OrderItem;

import java.util.List;

public interface OrderService {
    void createOrder(Order order, List<OrderItem> items);
    List<Order> getOrdersByUserId(Long userId);
    void deleteOrderById(Long orderId);
}

