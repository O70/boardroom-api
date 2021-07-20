package org.thraex.boardroom.common.constant;

import org.thraex.boardroom.common.converter.IntegerOperator;

import java.util.stream.Stream;

/**
 * 预定订单状态
 *
 * @author 鬼王
 * @date 2021/07/15 11:39
 */
public enum OrderStatus implements IntegerOperator {

    NEWEST(20, "最新"),
    NON_NEWEST(21, "非最新"),
    CANCELLED(30, "已取消");

    private final int value;

    private final String phrase;

    OrderStatus(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    @Override
    public Integer value() {
        return this.value;
    }

    public static OrderStatus of(final int value) {
        return Stream.of(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format("%s %d %s", name(), value, phrase);
    }

}
