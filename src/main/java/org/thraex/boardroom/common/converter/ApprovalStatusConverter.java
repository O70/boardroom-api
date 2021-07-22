package org.thraex.boardroom.common.converter;

import org.thraex.boardroom.common.constant.ApprovalStatus;
import org.thraex.toolkit.jpa.IntegerEnumAttributeConverter;

import javax.persistence.Converter;

/**
 * @author 鬼王
 * @date 2021/07/19 14:56
 */
@Converter(autoApply = true)
public class ApprovalStatusConverter extends IntegerEnumAttributeConverter<ApprovalStatus> {
}
