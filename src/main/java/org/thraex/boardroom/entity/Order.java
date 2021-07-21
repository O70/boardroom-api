package org.thraex.boardroom.entity;

import org.thraex.boardroom.common.constant.ApprovalStatus;
import org.thraex.boardroom.common.constant.BookingType;
import org.thraex.toolkit.entity.BaseEntity;

/**
 * 会议室预定订单记录
 *
 * @author 鬼王
 * @date 2021/07/14 16:49
 */
public class Order extends BaseEntity<Order> {

    private BookingType type;

    private ApprovalStatus status;

    public BookingType getType() {
        return type;
    }

    public Order setType(BookingType type) {
        this.type = type;
        return this;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public Order setStatus(ApprovalStatus status) {
        this.status = status;
        return this;
    }

}
