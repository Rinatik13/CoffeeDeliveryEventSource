package com.example.testdelivery.service;

import com.example.testdelivery.entity.Order;
import com.example.testdelivery.entity.OrderEvent;

public interface OrderService {
    void publishEvent(OrderEvent event);
    Order findOrder(int id);
}
