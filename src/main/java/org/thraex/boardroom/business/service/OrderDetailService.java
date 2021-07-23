package org.thraex.boardroom.business.service;

import org.springframework.stereotype.Service;
import org.thraex.boardroom.business.repository.OrderDetailRepository;

/**
 * @author 鬼王
 * @date 2021/07/23 10:43
 */
@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

}
