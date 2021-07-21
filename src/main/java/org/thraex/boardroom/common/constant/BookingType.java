package org.thraex.boardroom.common.constant;

import org.thraex.boardroom.common.converter.IntegerOperator;

/**
 * 预定类型
 *
 * @author 鬼王
 * @date 2021/07/15 15:21
 */
public enum BookingType implements IntegerOperator {

    ORDINARY(10, "普通预定"),
    PERIODIC(20, "周期预定"),
    LONG_TERM(30, "长期预定");

    private final int value;

    private final String phrase;

    BookingType(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    @Override
    public int value() {
        return this.value;
    }

    public static BookingType of(int value) {
        return IntegerOperator.find(values(), value);
    }

    @Override
    public String toString() {
        return String.format("%s %d %s", name(), value, phrase);
    }

}
