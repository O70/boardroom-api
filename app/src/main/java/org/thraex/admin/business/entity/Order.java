package org.thraex.admin.business.entity;

import org.thraex.admin.base.constant.BookingType;
import org.thraex.toolkit.entity.JpaEntity;

import javax.persistence.Entity;

/**
 * 预定订单记录
 *
 * @author 鬼王
 * @date 2021/07/14 16:49
 */
@Entity
public class Order extends JpaEntity<Order> {

    private BookingType type;

    public BookingType getType() {
        return type;
    }

    public Order setType(BookingType type) {
        this.type = type;
        return this;
    }

}
