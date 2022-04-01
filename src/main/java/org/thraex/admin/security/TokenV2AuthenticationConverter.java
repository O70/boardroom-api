package org.thraex.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import org.thraex.admin.system.entity.User;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.io.IOException;
import java.util.logging.Level;

/**
 * TODO: Opt Exception
 *
 * @author 鬼王
 * @date 2022/03/25 14:03
 */
public class TokenV2AuthenticationConverter implements ServerAuthenticationConverter {

    private Logger logger = Loggers.getLogger(TokenV2AuthenticationConverter.class);

    private TokenProcessor tokenProcessor;
    private final String prefix;

    public TokenV2AuthenticationConverter(TokenProcessor tokenProcessor) {
        this.tokenProcessor = tokenProcessor;
        this.prefix = tokenProcessor.getProperties().getPrefix();
    }

    public static TokenV2AuthenticationConverter of(TokenProcessor tokenProcessor) {
        return new TokenV2AuthenticationConverter(tokenProcessor);
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .log(logger, Level.INFO, false, SignalType.ON_SUBSCRIBE, SignalType.ON_NEXT)
                .filter(a -> StringUtils.startsWithIgnoreCase(a, prefix))
                .flatMap(this::apply);
    }

    private Mono<Authentication> apply(String authorization) {
        try {
            JwtClaims claims = tokenProcessor.verify(authorization);
            logger.debug("Claims: {}", claims);

            String principal = claims.getClaimValueAsString("principal");
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(principal, User.class);

            return Mono.just(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
        } catch (InvalidJwtException | IOException e) {
            logger.error(e.getMessage());
        }

        return Mono.empty();
    }

}
