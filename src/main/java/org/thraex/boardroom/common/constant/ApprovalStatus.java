package org.thraex.boardroom.common.constant;

import java.util.Arrays;

/**
 * 审批状态
 *
 * @author 鬼王
 * @date 2021/07/15 08:34
 */
public enum ApprovalStatus {

    APPROVAL(10, "审批中"),
    RETURNED(19, "审批退回"),
    APPROVED(20, "审批通过");

    private final int value;

    private final String phrase;

    ApprovalStatus(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    public int value() {
        return this.value;
    }

    public static ApprovalStatus of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name() + " " + this.value + " " + this.phrase;
    }

}
