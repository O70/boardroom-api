package org.thraex.admin.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.thraex.admin.system.repository.UserRepository;

/**
 * @author 鬼王
 * @date 2022/03/18 16:47
 */
@Import({TokenProcessor.class,
        LoginAuthenticationWebFilter.class,
        LoginAuthenticationSuccessHandler.class,
        TokenAuthenticationWebFilter.class })
@EnableConfigurationProperties(TokenProperties.class)
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    ReactiveAuthenticationManager authenticationManager(UserRepository userRepository) {
        JpaUserDetailsService service = JpaUserDetailsService.of(userRepository);
        return new UserDetailsRepositoryReactiveAuthenticationManager(service);
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                               ReactiveAuthenticationManager manager,
                                               LoginAuthenticationWebFilter loginAuthenticationWebFilter,
                                               TokenAuthenticationWebFilter tokenAuthenticationWebFilter) {
        http.authorizeExchange().anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authenticationManager(manager)
                .addFilterAt(loginAuthenticationWebFilter, SecurityWebFiltersOrder.HTTP_BASIC)
                .addFilterAt(tokenAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

}
