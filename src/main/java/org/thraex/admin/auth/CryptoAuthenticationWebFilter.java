package org.thraex.admin.auth;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.MediaTypeServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * @author 鬼王
 * @date 2022/03/21 22:18
 */
@Deprecated
public class CryptoAuthenticationWebFilter implements WebFilter {

    private ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers
            .pathMatchers(HttpMethod.POST, "/auth/login");

    public void tt() {
        AuthenticationWebFilter authenticationFilter = new AuthenticationWebFilter(
                null);
        MediaTypeServerWebExchangeMatcher jsonMatcher = new MediaTypeServerWebExchangeMatcher(MediaType.APPLICATION_JSON);
        jsonMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
        //ServerHttpSecurity
        NoOpServerSecurityContextRepository.getInstance();

    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Mono<ServerWebExchangeMatcher.MatchResult> matches = this.matcher.matches(exchange);
        //matches
        return null;
    }

}
