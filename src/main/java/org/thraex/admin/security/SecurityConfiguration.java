package org.thraex.admin.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.thraex.admin.generics.response.ResponseResult;
import org.thraex.admin.generics.response.ResponseStatus;
import org.thraex.admin.system.repository.UserRepository;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Set;

/**
 * @author 鬼王
 * @date 2022/03/18 16:47
 */
@Import(TokenProcessor.class)
@ConfigurationProperties("thraex.security")
@EnableConfigurationProperties(TokenProperties.class)
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private Set<String> permitted = Collections.EMPTY_SET;

    @Bean
    ReactiveAuthenticationManager authenticationManager(UserRepository userRepository) {
        JpaUserDetailsService service = JpaUserDetailsService.of(userRepository);
        return new UserDetailsRepositoryReactiveAuthenticationManager(service);
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                               ReactiveAuthenticationManager manager,
                                               TokenProcessor tokenProcessor) {
        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();
        if (!permitted.isEmpty()) {
            authorizeExchange.pathMatchers(permitted.toArray(new String[0])).permitAll();
        }

        authorizeExchange.anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authenticationManager(manager)
                .addFilterAt(LoginAuthenticationWebFilter.of(manager, tokenProcessor), SecurityWebFiltersOrder.HTTP_BASIC)
                .addFilterAt(TokenAuthenticationWebFilter.of(tokenProcessor), SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling().accessDeniedHandler((exchange, e) -> Mono.defer(() ->
                    ServerHttpResponseWriter.write(exchange, HttpStatus.FORBIDDEN,
                        ResponseResult.fail(ResponseStatus.AUTHENTICATION_ACCESS_DENIED))));

        return http.build();
    }

    public void setPermitted(Set<String> permitted) {
        this.permitted = permitted;
    }

}
