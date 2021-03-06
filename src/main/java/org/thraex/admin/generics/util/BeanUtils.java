package org.thraex.admin.generics.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/08/25 10:55
 */
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void copyProperties(Object source, Object target, boolean ignoreNull, String... canNullProperties) {
        String[] ignoreProperties = ignoreNull ? getNullProperties(source, canNullProperties) : null;
        copyProperties(source, target, ignoreProperties);
    }

    public static String[] getNullProperties(Object source, String... ignoreProperties) {
        Assert.notNull(source, "Source must not be null");

        BeanWrapper wrapper = new BeanWrapperImpl(source);
        List<String> ignores = Arrays.asList(ArrayUtils.nullToEmpty(ignoreProperties));
        Predicate<String> isNull = n -> !ignores.contains(n) && Objects.isNull(wrapper.getPropertyValue(n));

        return Stream.of(wrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(isNull)
                .toArray(String[]::new);
    }

}
