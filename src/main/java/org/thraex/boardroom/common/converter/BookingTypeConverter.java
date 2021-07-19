package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.BookingType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class BookingTypeConverter implements AttributeConverter<BookingType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookingType attribute) {
        return Optional.ofNullable(attribute)
                .map(it -> it.value())
                .orElse(null);
    }

    @Override
    public BookingType convertToEntityAttribute(Integer value) {
        return BookingType.of(value);
    }

}
