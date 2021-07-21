package org.thraex.boardroom.common.constant;

import org.thraex.boardroom.common.converter.IntegerOperator;

/**
 * 审批状态
 *
 * @author 鬼王
 * @date 2021/07/15 08:34
 */
public enum ApprovalStatus implements IntegerOperator {

    APPROVAL(10, "审批中"),
    RETURNED(19, "审批退回"),
    APPROVED(20, "审批通过");

    private final int value;

    private final String phrase;

    ApprovalStatus(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    @Override
    public int value() {
        return this.value;
    }

    public static ApprovalStatus of(int value) {
        return IntegerOperator.find(values(), value);
    }

    @Override
    public String toString() {
        return String.format("%s %d %s", name(), value, phrase);
    }

}
