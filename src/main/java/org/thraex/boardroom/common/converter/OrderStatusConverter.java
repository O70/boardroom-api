package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.OrderStatus;

import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class OrderStatusConverter extends IntegerAttributeConverter<OrderStatus> {

    @Override
    public OrderStatus convertToEntityAttribute(Integer value) {
        return Optional.ofNullable(value)
                .map(OrderStatus::of)
                .orElse(null);
    }

}
