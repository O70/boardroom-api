package org.thraex.boardroom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thraex.toolkit.aop.JpaEntityAspect;

/**
 * @author 鬼王
 * @date 2021/08/26 15:46
 */
@Configuration
@Import(JpaEntityAspect.class)
public class ImportConfiguration {
}
