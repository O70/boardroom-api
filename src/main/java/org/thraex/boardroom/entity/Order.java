package org.thraex.boardroom.entity;

import org.thraex.boardroom.constant.ApprovalStatus;
import org.thraex.boardroom.constant.BookingType;
import org.thraex.toolkit.entity.Entity;

/**
 * 会议室预定订单记录
 *
 * @author 鬼王
 * @date 2021/07/14 16:49
 */
public class Order extends Entity<Order> {

    /**
     * @see {@link Room#getId()}
     */
    private String roomId;

    private BookingType type;

    private ApprovalStatus status;

    public String getRoomId() {
        return roomId;
    }

    public Order setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

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
