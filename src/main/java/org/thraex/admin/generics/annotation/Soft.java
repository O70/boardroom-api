package org.thraex.admin.generics.annotation;

import org.hibernate.annotations.Where;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 鬼王
 * @date 2022/03/14 09:37
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Inherited
@Where(clause = "deleted = false")
@Deprecated
public @interface Soft {
}
