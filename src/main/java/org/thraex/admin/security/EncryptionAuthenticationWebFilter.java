package org.thraex.admin.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/22 18:44
 */
@Deprecated
public class EncryptionAuthenticationWebFilter implements ServerAuthenticationConverter {

    private Logger logger = Loggers.getLogger(EncryptionAuthenticationWebFilter.class);

    public static EncryptionAuthenticationWebFilter of() {
        return new EncryptionAuthenticationWebFilter();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Optional.of(exchange.getRequest())
                .map(ServerHttpRequest::getHeaders)
                .map(headers -> headers.getFirst(HttpHeaders.AUTHORIZATION))
                .filter(StringUtils::isNotBlank)
                .map(this::apply)
                .orElse(Mono.empty());
    }

    private Mono<Authentication> apply(String authorization) {
        if (logger.isDebugEnabled()) {
            logger.debug("Authorization: [{}]", authorization);
        }

        String[] parts = authorization.split(":");

        if (parts.length != 2) {
            return Mono.empty();
        }

        String username = parts[0];
        String password = parts[1];

        if (logger.isDebugEnabled()) {
            logger.debug("Authorization username:password: [{}:{}]", username, password);
        }

        return Mono.just(new UsernamePasswordAuthenticationToken(username, password));
    }

}
