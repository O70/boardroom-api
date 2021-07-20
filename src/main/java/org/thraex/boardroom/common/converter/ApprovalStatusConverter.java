package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.ApprovalStatus;

import javax.persistence.Converter;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class ApprovalStatusConverter extends IntegerAttributeConverter<ApprovalStatus> {

    @Override
    public ApprovalStatus convertToEntityAttribute(Integer value) {
        return Optional.ofNullable(value)
                .map(ApprovalStatus::of)
                .orElse(null);
    }

}
