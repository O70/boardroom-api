package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.BookingType;

import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class BookingTypeConverter extends IntegerAttributeConverter<BookingType> {

    /*@Override
    public BookingType convertToEntityAttribute(Integer value) {
        return Optional.ofNullable(value)
                .map(BookingType::of)
                .orElse(null);
    }*/

}
