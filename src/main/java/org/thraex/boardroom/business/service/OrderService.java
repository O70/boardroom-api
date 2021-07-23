package org.thraex.boardroom.business.service;

import org.springframework.stereotype.Service;
import org.thraex.boardroom.business.repository.OrderRepository;

/**
 * @author 鬼王
 * @date 2021/07/23 10:23
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

}
