package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.BookingType;
import org.thraex.boardroom.common.constant.OrderStatus;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Stream;

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
        //((ParameterizedTypeImpl) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        Class<? extends Type> aClass = actualTypeArguments[0].getClass();
        //((Class) actualTypeArguments[0]).getEnumConstants()
        //X.of2(value, actualTypeArguments[0]);
        //((Class) actualTypeArguments[0]).getEnumConstants()[0].
        Object[] enumConstants = ((Class) actualTypeArguments[0]).getEnumConstants();
        Stream.of(enumConstants).forEach(it -> {
            try {
                Method value1 = it.getClass().getMethod("value");
                System.out.println(value1);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            //it.value() == value;
        });
        //return X.of1(value);
        return null;
    }

    public static void main(String[] args) {
        //BookingType.valueOf()
        //System.out.println(BookingType.of(null));
        System.out.println(BookingType.valueOf(OrderStatus.class, "NON_NEWEST"));

        System.out.println(BookingType.valueOf(BookingType.class, "LONG_TERM"));
        System.out.println(BookingType.valueOf("LONG_TERM"));

        //System.out.println(BookingType.of(BookingType.class, 10));
        //System.out.println(IntegerOperator.te);
        //System.out.println(BookingType.of);
        //System.out.println(BookingType.of(null));
        System.out.println(BookingType.of(1));
        System.out.println(BookingType.of(30));
    }

}
