package org.thraex.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.thraex.admin.auth.JpaUserDetailsService;
import org.thraex.admin.auth.RestServerAuthenticationSuccessHandler;
import org.thraex.admin.system.repository.UserRepository;

/**
 * @author 鬼王
 * @date 2022/03/18 16:47
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    private ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/auth/login");

    @Bean
    ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
        return new JpaUserDetailsService(userRepository);
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                               ReactiveUserDetailsService userDetailsService) {
        //UserDetails user = User
        //        .withUsername("master1")
        //        .password("admin")
        //        .roles("SUPER_ADMIN")
        //        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
        //        .build();

        //MapReactiveUserDetailsService userDetailsService = new MapReactiveUserDetailsService(user);
        //ReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());

        //UsernamePasswordAuthenticationFilter upFilter = new UsernamePasswordAuthenticationFilter();
        //upFilter.setRequiresAuthenticationRequestMatcher();
        //upFilter.setAuth

        ReactiveAuthenticationManager manager = authenticationManager(userDetailsService);
        http.authorizeExchange()
                //.pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authenticationManager(manager)
                .addFilterAt(webFilter(manager), SecurityWebFiltersOrder.AUTHENTICATION)
                //.exceptionHandling()
                //.accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.UNAUTHORIZED))
                //.httpBasic()
                //.and()
                //.formLogin()
                //.and()

                //.addFilterAt()
                //.exceptionHandling().accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN))
                //.securityContextRepository()
        ;

        return http.build();
    }

    private ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    }

    private AuthenticationWebFilter webFilter(ReactiveAuthenticationManager manager) {
        //MediaTypeServerWebExchangeMatcher jsonMatcher = new MediaTypeServerWebExchangeMatcher(
        //        MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
        //jsonMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));

        AuthenticationWebFilter filter = new AuthenticationWebFilter(manager);
        filter.setRequiresAuthenticationMatcher(matcher);
        filter.setAuthenticationSuccessHandler(RestServerAuthenticationSuccessHandler.of());
        HttpStatusServerEntryPoint entryPoint = new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED);
        filter.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));

        return filter;
    }

}
