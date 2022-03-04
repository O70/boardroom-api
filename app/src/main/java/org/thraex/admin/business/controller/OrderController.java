package org.thraex.admin.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.base.constant.BookingType;
import org.thraex.admin.base.constant.OrderState;
import org.thraex.admin.business.entity.Order;
import org.thraex.admin.business.entity.OrderDetail;
import org.thraex.admin.business.service.OrderDetailService;
import org.thraex.admin.business.service.OrderService;
import org.thraex.toolkit.constant.Whether;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @PostMapping("mock")
    public List<OrderDetail> mockSave() {
        Order order = new Order();
        order.setType(BookingType.ORDINARY);
        orderService.save(order);

        List<OrderDetail> collect = IntStream.range(0, 5).mapToObj(i -> {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setSubject("主题-" + i);
            detail.setSecret(Whether.NO);
            detail.setNum(10 + i);
            detail.setState(OrderState.APPROVAL);

            return detail;
        }).collect(Collectors.toList());
        orderDetailService.saveAll(collect);

        return collect;
    }

    @GetMapping("mock")
    public List<OrderDetail> mockList() {
        return orderDetailService.findAll();
    }

    @GetMapping("mock/{id}")
    public Order mockOne(@PathVariable String id) {
       return orderService.findById(id).orElse(null);
    }

    @GetMapping("mock/enum")
    public OrderState mockEnum() {
        return OrderState.APPROVAL;
    }

}
