package org.thraex.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/08/30 14:27
 */
@Configuration
@EnableJpaAuditing(modifyOnCreate = false)
public class AuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: Get user id
        return Optional.of("HANZO");
    }

}
