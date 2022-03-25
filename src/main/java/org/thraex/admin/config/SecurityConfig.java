package org.thraex.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.thraex.admin.auth.CryptoAuthenticationWebFilter;
import org.thraex.admin.auth.JpaUserDetailsService;
import org.thraex.admin.auth.RestAuthenticationConverter;
import org.thraex.admin.auth.RestServerAuthenticationFailureHandler;
import org.thraex.admin.auth.RestServerAuthenticationSuccessHandler;
import org.thraex.admin.system.repository.UserRepository;

/**
 * @author 鬼王
 * @date 2022/03/18 16:47
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    ReactiveAuthenticationManager authenticationManager(UserRepository userRepository) {
        JpaUserDetailsService userDetailsService = new JpaUserDetailsService(userRepository);
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, ReactiveAuthenticationManager manager) {
        http.authorizeExchange()
                //.pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                //.httpBasic()
                //.and()
                //.formLogin()
                //.and()
                .authenticationManager(manager)
                //.addFilterAt(webFilter(manager), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAt(CryptoAuthenticationWebFilter.of(), SecurityWebFiltersOrder.AUTHENTICATION)
                //.exceptionHandling()
                //.accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.UNAUTHORIZED))
        ;

        return http.build();
    }

    private AuthenticationWebFilter webFilter(ReactiveAuthenticationManager manager) {
        //MediaTypeServerWebExchangeMatcher jsonMatcher = new MediaTypeServerWebExchangeMatcher(
        //        MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
        //jsonMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));

        ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/auth/login");
        AuthenticationWebFilter filter = new AuthenticationWebFilter(manager);
        filter.setRequiresAuthenticationMatcher(matcher);
        filter.setAuthenticationSuccessHandler(RestServerAuthenticationSuccessHandler.of());
        filter.setAuthenticationFailureHandler(RestServerAuthenticationFailureHandler.of());
        //HttpStatusServerEntryPoint entryPoint = new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED);
        //filter.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
        filter.setServerAuthenticationConverter(RestAuthenticationConverter.of());
        //filter.setServerAuthenticationConverter(new ServerFormLoginAuthenticationConverter());

        return filter;
    }

}
