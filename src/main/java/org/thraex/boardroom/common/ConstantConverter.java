package org.thraex.boardroom.common;

import org.thraex.boardroom.common.constant.ApprovalStatus;
import org.thraex.boardroom.common.constant.BookingType;
import org.thraex.boardroom.common.constant.OrderStatus;
import org.thraex.toolkit.jpa.IntegerEnumAttributeConverter;

import javax.persistence.Converter;

/**
 * @author 鬼王
 * @date 2021/07/22 13:23
 */
public final class ConstantConverter {

    @Converter(autoApply = true)
    public static class BookingTypeConverter extends IntegerEnumAttributeConverter<BookingType> {}

    @Converter(autoApply = true)
    public static class OrderStatusConverter extends IntegerEnumAttributeConverter<OrderStatus> {}

    @Converter(autoApply = true)
    public static class ApprovalStatusConverter extends IntegerEnumAttributeConverter<ApprovalStatus> {}

}
