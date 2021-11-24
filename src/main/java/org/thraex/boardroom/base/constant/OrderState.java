package org.thraex.boardroom.base.constant;

import org.thraex.toolkit.jpa.IntegerEnumAttributeOperator;

/**
 * @author 鬼王
 * @date 2021/07/15 11:39
 */
public enum OrderState implements IntegerEnumAttributeOperator {

    APPROVAL(10, "审批中"),
    RETURNED(19, "审批退回"),
    APPROVED_NEW(20, "审批通过"),
    APPROVED_NON_NEW(21, "审批通过"),
    CANCELLED(30, "已取消");

    private final int value;

    private final String phrase;

    OrderState(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    @Override
    public Integer value() {
        return this.value;
    }

    public static OrderState of(Integer value) {
        return IntegerEnumAttributeOperator.find(values(), value);
    }

    @Override
    public String toString() {
        return String.format("%s %d %s", name(), value, phrase);
    }

}