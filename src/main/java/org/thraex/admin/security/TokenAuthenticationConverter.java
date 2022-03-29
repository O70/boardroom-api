package org.thraex.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import org.thraex.admin.system.entity.User;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author 鬼王
 * @date 2022/03/25 14:03
 */
public class TokenAuthenticationConverter implements ServerAuthenticationConverter {

    private Logger logger = LoggerFactory.getLogger(TokenAuthenticationConverter.class);

    private TokenProcessor tokenProcessor;
    private final String prefix;

    public TokenAuthenticationConverter(TokenProcessor tokenProcessor) {
        this.tokenProcessor = tokenProcessor;
        this.prefix = tokenProcessor.getProperties().getPrefix();
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

        if (!StringUtils.startsWithIgnoreCase(authorization, prefix)) {
            return Mono.empty();
        }

        try {
            JwtClaims claims = tokenProcessor.verify(authorization);

            System.out.println(claims);
            String principal = claims.getClaimValueAsString("principal");
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(principal, User.class);
            System.out.println(user);

            return Mono.just(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
        } catch (InvalidJwtException | IOException e) {
            e.printStackTrace();
        }

        return Mono.empty();
    }

}
