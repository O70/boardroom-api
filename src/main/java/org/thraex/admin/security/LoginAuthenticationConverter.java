package org.thraex.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import org.thraex.admin.generics.util.RSAUtil;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author 鬼王
 * @date 2022/03/22 18:44
 */
public class LoginAuthenticationConverter implements ServerAuthenticationConverter {

    private Logger logger = LoggerFactory.getLogger(LoginAuthenticationConverter.class);

    private final String privateKey;

    public LoginAuthenticationConverter(String privateKey) {
        this.privateKey = privateKey;
    }

    public static LoginAuthenticationConverter of(String privateKey) {
        return new LoginAuthenticationConverter(privateKey);
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        return Mono.justOrEmpty(headers.getFirst(HttpHeaders.AUTHORIZATION))
                .filter(StringUtils::isNotBlank)
                .flatMap(this::apply);
    }

    private Mono<Authentication> apply(String authorization) {
        logger.info("Authorization: [{}]", authorization);

        try {
            String decrypted = RSAUtil.decrypt(authorization, privateKey);
            ObjectMapper mapper = new ObjectMapper();
            Params params = mapper.readValue(decrypted, Params.class);

            return Mono.just(new UsernamePasswordAuthenticationToken(params.getUsername(), params.getPassword()));
        } catch (GeneralSecurityException | IOException e) {
            logger.error(e.getMessage());
        }

        return Mono.empty();
    }

    private static class Params {

        private String username;

        private String password;

        private String code;

        public String getUsername() {
            return username;
        }

        public Params setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public Params setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getCode() {
            return code;
        }

        public Params setCode(String code) {
            this.code = code;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }

    }

}
