package org.thraex.admin.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.server.ServerWebExchange;
import org.thraex.admin.generics.response.ResponseResult;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/22 17:40
 */
public class RestServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(RestServerAuthenticationSuccessHandler.class);

    public static RestServerAuthenticationSuccessHandler of() {
        return new RestServerAuthenticationSuccessHandler();
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        logger.info("Authentication success: [{}]", authentication.getName());

        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse result = exchange.getResponse();
        result.setStatusCode(HttpStatus.OK);
        result.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseResult<String> data = ResponseResult.ok("TOKEN is XXXXX!");
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer wrap = bufferFactory.wrap(bytes);

        return result.writeWith(Mono.just(wrap));
    }

}
