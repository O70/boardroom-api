package org.thraex.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
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

    private ServerSecurityContextRepository securityContextRepository = NoOpServerSecurityContextRepository.getInstance();

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
                .flatMap(authentication -> onAuthenticationSuccess(exchange, chain, authentication));
    }

    protected Mono<Void> onAuthenticationSuccess(ServerWebExchange exchange,
                                                 WebFilterChain chain,
                                                 Authentication authentication) {
        SecurityContextImpl securityContext = new SecurityContextImpl(authentication);

        return securityContextRepository.save(exchange, securityContext)
                .thenEmpty(chain.filter(exchange).then(Mono.empty()))
                .subscriberContext(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
    }

}
