package org.thraex.admin.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author 鬼王
 * @date 2022/03/22 18:44
 */
public class RestAuthenticationConverter implements ServerAuthenticationConverter {

    private Logger logger = LoggerFactory.getLogger(RestAuthenticationConverter.class);

    public static RestAuthenticationConverter of() {
        return new RestAuthenticationConverter();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return apply(exchange);
    }

    private Mono<Authentication> apply(ServerWebExchange exchange) {
        //Mono<DataBuffer> mono = exchange.getRequest().getBody().single().switchIfEmpty(Mono.empty());
        return exchange.getRequest().getBody().single().map(it -> {
            Params p = Params.of(it);

            return new UsernamePasswordAuthenticationToken(p.getUsername(), p.getPassword());
        });

        //return mono.map(Params::of).map(p -> new UsernamePasswordAuthenticationToken(p.getUsername(), p.getPassword()));
    }

    private static class Params {

        private String username;

        private String password;

        static Params of(DataBuffer buffer) {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);

            ObjectMapper mapper = new ObjectMapper();
            Params params = null;
            try {
                params = mapper.readValue(bytes, Params.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return params;
        }

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

    }

}
