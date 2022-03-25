package org.thraex.admin.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/25 14:03
 */
public class ServerTokenAuthenticationConverter implements ServerAuthenticationConverter {

    public static ServerTokenAuthenticationConverter of() {
        return new ServerTokenAuthenticationConverter();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("master", "master");
        return Mono.just(token);
    }

}
