package org.thraex.boardroom.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.base.constant.BookingType;
import org.thraex.boardroom.business.entity.Order;
import org.thraex.boardroom.business.entity.OrderDetail;
import org.thraex.boardroom.business.service.OrderDetailService;
import org.thraex.boardroom.business.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鬼王
 * @date 2021/07/23 10:22
 */
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(OrderService orderService,
                           OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public Order save(@RequestBody OrderDetail detail) {
        /*OrderDetail orderDetail = new OrderDetail();
        Order order = new Order().setId("order-1").setType(BookingType.ORDINARY);
        orderDetail.setOrder(order);
        orderDetail.setRoom(new Room().setId("room-1"));
        orderDetail.setTypeId("type-1");
        orderDetail.setTypeName("名字");
        orderService.save(order);
        orderDetailService.save(orderDetail);*/

        Order order = orderService.save(new Order().setType(BookingType.ORDINARY));
        //OrderDetail save = orderDetailService.save(detail.setOrder(order));

        List<OrderDetail> details = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            OrderDetail od = new OrderDetail();
            od.setSubject(detail.getSubject() + "-" + i);
            od.setOrder(order);
            od.setRoom(detail.getRoom());
            details.add(od);
        }
        List<OrderDetail> details1 = orderDetailService.saveAll(details);

        return order;
    }

    @GetMapping
    public List<OrderDetail> list() {
        return orderDetailService.findAll();
    }

}
