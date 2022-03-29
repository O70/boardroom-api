package org.thraex.admin.security;

import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/25 14:03
 */
public class TokenAuthenticationConverter implements ServerAuthenticationConverter {

    private Logger logger = LoggerFactory.getLogger(TokenAuthenticationConverter.class);

    private TokenProcessor tokenProcessor;

    public TokenAuthenticationConverter(TokenProcessor tokenProcessor) {
        this.tokenProcessor = tokenProcessor;
    }

    public static TokenAuthenticationConverter of(TokenProcessor tokenProcessor) {
        return new TokenAuthenticationConverter(tokenProcessor);
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        return Mono.justOrEmpty(headers.getFirst(HttpHeaders.AUTHORIZATION))
                .filter(StringUtils::isNotBlank)
                .flatMap(this::apply)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials(Authorization Token)"))));
    }

    private Mono<Authentication> apply(String authorization) {
        logger.info("Authorization(Token): [{}]", authorization);

        try {
            JwtClaims claims = tokenProcessor.verify(authorization);
            System.out.println(claims);
        } catch (InvalidJwtException e) {
            e.printStackTrace();
        }

        return Mono.empty();
    }

}
