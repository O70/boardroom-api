package org.thraex.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.thraex.admin.generics.response.ResponseResult;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 鬼王
 * @date 2022/03/22 17:40
 */
public class LoginAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);

    private final TokenProcessor tokenProcessor;

    public LoginAuthenticationSuccessHandler(TokenProcessor tokenProcessor) {
        this.tokenProcessor = tokenProcessor;
    }

    public static LoginAuthenticationSuccessHandler of(TokenProcessor tokenProcessor) {
        return new LoginAuthenticationSuccessHandler(tokenProcessor);
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange exchange, Authentication authentication) {
        logger.info("Authentication success: [{}]", authentication.getName());

        String token = tokenProcessor.generate(claims -> {
            //User user = (User) authentication.getPrincipal();
            //Object credentials = authentication.getCredentials();
            //Collection<? extends GrantedAuthority> authorities1 = authentication.getAuthorities();
            //Object details = authentication.getDetails();
            //String name = authentication.getName();

            claims.setSubject(authentication.getName());
            claims.setClaim("principal", authentication.getPrincipal());
            //List<String> authorities = authentication.getAuthorities().parallelStream()
            //        .map(it -> it.getAuthority()).collect(Collectors.toList());
            //claims.setStringListClaim("authorities", authorities);
        });

        return LoginAuthenticationWriter.write(exchange, ResponseResult.ok(token));
    }

}
