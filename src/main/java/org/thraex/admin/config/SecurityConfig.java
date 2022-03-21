package org.thraex.admin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler;

/**
 * @author 鬼王
 * @date 2022/03/18 16:47
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    /*@Bean
    MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("master1")
                .password("admin")
                .roles("SUPER_ADMIN")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .build();

        return new MapReactiveUserDetailsService(user);
    }*/

    /*@Bean
    ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService us) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(us);
    }*/

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        //UserDetails user = User
        //        .withUsername("master1")
        //        .password("admin")
        //        .roles("SUPER_ADMIN")
        //        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
        //        .build();

        //MapReactiveUserDetailsService userDetailsService = new MapReactiveUserDetailsService(user);
        //ReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());

        http.authorizeExchange()
                //.pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable();
                //.and()
                //.authenticationManager(authenticationManager)
                //.exceptionHandling()
                //.accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.UNAUTHORIZED))
                //.httpBasic();
                //.securityContextRepository()
        ;

        return http.build();
    }

}
