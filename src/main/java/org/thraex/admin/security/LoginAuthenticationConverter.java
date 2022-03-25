package org.thraex.admin.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/22 18:44
 */
public class LoginAuthenticationConverter implements ServerAuthenticationConverter {

    private Logger logger = LoggerFactory.getLogger(LoginAuthenticationConverter.class);

    public static LoginAuthenticationConverter of() {
        return new LoginAuthenticationConverter();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        //Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
        //        .filter(StringUtils::isNotBlank);
        return Mono.just(exchange.getRequest())
                .map(ServerHttpRequest::getHeaders)
                .flatMap(headers -> Mono.justOrEmpty(headers.getFirst(HttpHeaders.AUTHORIZATION)))
                //.filter(StringUtils::isNotBlank)
                .filter(it -> StringUtils.isNotBlank(it))
                .flatMap(this::apply);
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
