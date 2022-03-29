package org.thraex.admin.security;

import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/28 20:07
 */
public class TokenAuthenticationWebFilter implements WebFilter {

    private ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers.anyExchange();

    public static TokenAuthenticationWebFilter of() {
        return new TokenAuthenticationWebFilter();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //return this.matcher.matches(exchange);
        return Mono.empty();
    }

}
