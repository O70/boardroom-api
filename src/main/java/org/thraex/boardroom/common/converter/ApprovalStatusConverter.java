package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.ApprovalStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class ApprovalStatusConverter implements AttributeConverter<ApprovalStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ApprovalStatus attribute) {
        return Optional.ofNullable(attribute)
                .map(it -> it.value())
                .orElse(null);
    }

    @Override
    public ApprovalStatus convertToEntityAttribute(Integer value) {
        return ApprovalStatus.of(value);
    }

}
