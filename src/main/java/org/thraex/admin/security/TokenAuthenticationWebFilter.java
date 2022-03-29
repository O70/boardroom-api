package org.thraex.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.Assert;
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

    private ServerAuthenticationConverter authenticationConverter;

    public TokenAuthenticationWebFilter(TokenProcessor tokenProcessor) {
        Assert.notNull(tokenProcessor, "tokenProcessor cannot be null");
        this.authenticationConverter = TokenAuthenticationConverter.of(tokenProcessor);
    }

    public static TokenAuthenticationWebFilter of(TokenProcessor tokenProcessor) {
        return new TokenAuthenticationWebFilter(tokenProcessor);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return matcher.matches(exchange)
                .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .flatMap(matchResult -> authenticationConverter.convert(exchange))
                .flatMap(token -> authenticate(null, token))
                ;
    }

    private Mono<Void> authenticate(WebFilterExchange exchange, Authentication token) {
        return null;
    }

}
