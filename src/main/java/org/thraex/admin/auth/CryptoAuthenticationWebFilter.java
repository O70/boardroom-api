package org.thraex.admin.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/21 22:18
 */
public class CryptoAuthenticationWebFilter implements WebFilter {

    private ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers
            .pathMatchers(HttpMethod.POST, "/auth/login");

    public CryptoAuthenticationWebFilter of() {
        return new CryptoAuthenticationWebFilter();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Mono<ServerWebExchangeMatcher.MatchResult> matches = this.matcher.matches(exchange);
        Mono<ServerWebExchangeMatcher.MatchResult> filter1 = matches.filter(ServerWebExchangeMatcher.MatchResult::isMatch);
        Mono<ServerWebExchangeMatcher.MatchResult> matchResultMono = filter1.switchIfEmpty(chain.filter(exchange).then(Mono.empty()));
        //matchResultMono.map()
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setBearerAuth();
        //httpHeaders.setBasicAuth();
        //httpHeaders.get

        return null;
    }

}
