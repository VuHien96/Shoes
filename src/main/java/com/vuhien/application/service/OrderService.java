package com.vuhien.application.service;

import com.vuhien.application.entity.Order;
import com.vuhien.application.model.request.CreateOrderRequest;
import com.vuhien.application.model.request.UpdateDetailOrder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Page<Order> adminGetListOrders(String id, String name, String phone, String status, String product, int page);

    Order createOrder(CreateOrderRequest createOrderRequest, long userId);

    void updateDetailOrder(UpdateDetailOrder updateDetailOrder, long id, long userId);

    Order findOrderById(long id);

}
