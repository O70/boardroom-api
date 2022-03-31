package org.thraex.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.thraex.admin.system.entity.User;
import reactor.core.publisher.Mono;

/**
 * TODO: https://github.com/spring-projects/spring-data-commons/issues/1670?utm_source=ld246.com
 *
 * @author 鬼王
 * @date 2022/03/31 14:36
 */
public abstract class SecurityHolder {

    public static Mono<Authentication> authentication() {
        return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication);
    }

    public static Mono<String> username() {
        return authentication().map(Authentication::getName);
    }

    public static Mono<User> principal() {
        return authentication().map(Authentication::getPrincipal).map(p -> (User) p);
    }

    public static Mono<String> id() {
        return principal().map(User::getId);
    }

}
