package org.thraex.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.thraex.admin.generics.response.ResponseResult;
import reactor.core.publisher.Mono;

/**
 * TODO: Generate TOKEN
 *
 * @author 鬼王
 * @date 2022/03/22 17:40
 */
public class LoginAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);

    public static LoginAuthenticationSuccessHandler of() {
        return new LoginAuthenticationSuccessHandler();
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange exchange, Authentication authentication) {
        logger.info("Authentication success: [{}]", authentication.getName());

        return LoginAuthenticationWriter.write(exchange, ResponseResult.ok("TOKEN is XXXXX-YYYY!"));
    }

}
