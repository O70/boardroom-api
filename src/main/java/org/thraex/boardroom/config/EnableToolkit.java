package org.thraex.boardroom.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 鬼王
 * @date 2021/08/30 17:23
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ AuditorAwareConfig.class, TemporalFormatConfig.class })
@Deprecated
public @interface EnableToolkit {
}
