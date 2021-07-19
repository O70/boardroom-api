package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus attribute) {
        return Optional.ofNullable(attribute)
                .map(it -> it.value())
                .orElse(null);
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer value) {
        return OrderStatus.of(value);
    }

}
