package org.thraex.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.thraex.admin.generics.response.ResponseResult;
import org.thraex.admin.generics.response.ResponseStatus;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/25 16:48
 */
public class LoginAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);

    public static LoginAuthenticationFailureHandler of() {
        return new LoginAuthenticationFailureHandler();
    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        logger.info("Authentication failure: [{}]", exception.getMessage());

        return LoginAuthenticationWriter.write(webFilterExchange,
                ResponseResult.fail(ResponseStatus.AUTHENTICATION_BAD_CREDENTIALS));
    }

}
