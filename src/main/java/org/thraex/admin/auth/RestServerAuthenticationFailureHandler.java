package org.thraex.admin.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/22 18:05
 */
@Deprecated
public class RestServerAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(RestServerAuthenticationFailureHandler.class);

    public static RestServerAuthenticationFailureHandler of() {
        return new RestServerAuthenticationFailureHandler();
    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        logger.info("Authentication failure: [{}]", exception.getMessage());

        return Mono.fromRunnable(() -> {
            ServerWebExchange exchange = webFilterExchange.getExchange();
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        });
    }

}
