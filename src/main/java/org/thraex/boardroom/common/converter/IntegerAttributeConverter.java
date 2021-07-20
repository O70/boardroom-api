package org.thraex.boardroom.common.converter;

import javax.persistence.AttributeConverter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 15:17
 */
public abstract class IntegerAttributeConverter<X extends IntegerOperator> implements AttributeConverter<X, Integer> {

    @Override
    public Integer convertToDatabaseColumn(X attribute) {
        return Optional.ofNullable(attribute)
                .map(X::value)
                .orElse(null);
    }

}
