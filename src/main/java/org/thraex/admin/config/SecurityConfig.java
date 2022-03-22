package org.thraex.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.thraex.admin.auth.JpaUserDetailsService;
import org.thraex.admin.system.repository.UserRepository;

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

    @Bean
    ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
        return new JpaUserDetailsService(userRepository);
    }

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

        //UsernamePasswordAuthenticationFilter upFilter = new UsernamePasswordAuthenticationFilter();
        //upFilter.setRequiresAuthenticationRequestMatcher();
        //upFilter.setAuth

        http.authorizeExchange()
                //.pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                //.authenticationManager(authenticationManager)
                //.exceptionHandling()
                //.accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.UNAUTHORIZED))
                .httpBasic()
                .and()
                .formLogin()
                .and()

                //.addFilterAt()
                //.exceptionHandling().accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN))
                //.securityContextRepository()
        ;

        return http.build();
    }

}
