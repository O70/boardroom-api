package org.thraex.admin.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * TODO: Solve [Only one connection receive subscriber allowed.]
 *
 *
 *
 * @author 鬼王
 * @date 2022/03/22 18:44
 */
@Deprecated
public class RestAuthenticationConverter implements ServerAuthenticationConverter {

    public static final String BASIC = "THRAEX ";

    public static RestAuthenticationConverter of() {
        return new RestAuthenticationConverter();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return apply(exchange);
    }

    private Mono<Authentication> apply(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();

        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.toLowerCase().startsWith("thraex ")) {
            return Mono.empty();
        }

        String credentials = authorization.length() <= BASIC.length() ?
                "" : authorization.substring(BASIC.length());
        String[] parts = credentials.split(":");

        if (parts.length != 2) {
            return Mono.empty();
        }

        String username = parts[0];
        String password = parts[1];

        return Mono.just(new UsernamePasswordAuthenticationToken(username, password));

        //Mono<DataBuffer> mono = exchange.getRequest().getBody().single().switchIfEmpty(Mono.empty());
        /*return exchange.getRequest().getBody().single().map(it -> {
            Params p = Params.of(it);
            logger.debug("{}", p);

            return new UsernamePasswordAuthenticationToken(p.getUsername(), p.getPassword());
        });*/

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
