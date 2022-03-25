package org.thraex.admin.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.web.server.ServerWebExchange;
import org.thraex.admin.generics.response.ResponseResult;
import reactor.core.publisher.Mono;

/**
 * @author 鬼王
 * @date 2022/03/25 17:35
 */
public abstract class LoginAuthenticationWriter {

    public static Mono<Void> write(WebFilterExchange webFilterExchange, ResponseResult<String> data) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer wrap = bufferFactory.wrap(bytes);

        return response.writeWith(Mono.just(wrap));
    }

}
