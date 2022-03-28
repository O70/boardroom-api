package org.thraex.admin.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private static final String PRIVATE_KEY = "${thraex.security.rsa.private-key}";

    @Bean
    ReactiveAuthenticationManager authenticationManager(UserRepository userRepository) {
        JpaUserDetailsService service = JpaUserDetailsService.of(userRepository);
        return new UserDetailsRepositoryReactiveAuthenticationManager(service);
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                               ReactiveAuthenticationManager manager,
                                               @Value(PRIVATE_KEY) String privateKey) {
        http.authorizeExchange().anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authenticationManager(manager)
                .addFilterAt(LoginAuthenticationWebFilter.of(manager, privateKey), SecurityWebFiltersOrder.HTTP_BASIC)
        ;

        return http.build();
    }

}
