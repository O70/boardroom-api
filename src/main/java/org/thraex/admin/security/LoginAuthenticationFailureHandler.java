package org.thraex.admin.security;

import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.thraex.admin.generics.response.ResponseResult;
import org.thraex.admin.generics.response.ResponseStatus;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

/**
 * @author 鬼王
 * @date 2022/03/25 16:48
 */
public class LoginAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    private Logger logger = Loggers.getLogger(LoginAuthenticationFailureHandler.class);

    public static LoginAuthenticationFailureHandler of() {
        return new LoginAuthenticationFailureHandler();
    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange exchange, AuthenticationException exception) {
        String message = exception.getMessage();
        logger.warn("Authentication failure: [{}]", message);

        ResponseStatus status = exception instanceof ProviderNotFoundException ?
                ResponseStatus.INTERNAL_SERVER_ERROR :
                ResponseStatus.AUTHENTICATION_BAD_CREDENTIALS;

        return ServerHttpResponseWriter.ok(exchange, ResponseResult.fail(status, message));
    }

}
