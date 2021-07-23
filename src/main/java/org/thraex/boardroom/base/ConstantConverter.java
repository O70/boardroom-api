package org.thraex.boardroom.base;

import org.thraex.boardroom.base.constant.ApprovalStatus;
import org.thraex.boardroom.base.constant.BookingType;
import org.thraex.boardroom.base.constant.OrderState;
import org.thraex.toolkit.constant.Whether;
import org.thraex.toolkit.jpa.IntegerEnumAttributeConverter;

import javax.persistence.Converter;

/**
 * @author 鬼王
 * @date 2021/07/22 13:23
 */
public final class ConstantConverter {

    @Converter(autoApply = true)
    public static class WhetherConverter extends IntegerEnumAttributeConverter<Whether> {}

    @Converter(autoApply = true)
    public static class BookingTypeConverter extends IntegerEnumAttributeConverter<BookingType> {}

    @Converter(autoApply = true)
    public static class OrderStatusConverter extends IntegerEnumAttributeConverter<OrderState> {}

    @Converter(autoApply = true)
    public static class ApprovalStatusConverter extends IntegerEnumAttributeConverter<ApprovalStatus> {}

}
