package org.thraex.boardroom.common.converter;

import javax.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;
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

    @Override
    public X convertToEntityAttribute(Integer value) {
        return X.find(this.getEnumConstants(), value);
    }

    private X[] getEnumConstants() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class actualType = (Class) type.getActualTypeArguments()[0];
        X[] xs = (X[]) actualType.getEnumConstants();

        return xs;
    }

}
