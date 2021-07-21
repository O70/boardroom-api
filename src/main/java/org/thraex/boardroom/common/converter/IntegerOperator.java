package org.thraex.boardroom.common.converter;

import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/07/19 15:38
 */
public interface IntegerOperator {

    int value();

    static <X extends IntegerOperator> X find(X[] values, int value) {
        return Stream.of(values)
                .filter(it -> it.value() == value)
                .findFirst()
                .orElse(null);
    }

}
