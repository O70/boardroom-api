package org.thraex.admin.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerHttpBasicAuthenticationConverter;
import org.springframework.security.web.server.ui.LoginPageGeneratingWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * <p>
 *     参考{@link AuthenticationWebFilter}和{@link LoginPageGeneratingWebFilter}
 * </p>
 *
 * <p>
 *     {@code httpBasic}与{@code formLogin}使用相应参数填充{@link AuthenticationWebFilter}。
 *
 *     <ul>
 *         <li>{@link ServerHttpSecurity.HttpBasicSpec#configure(ServerHttpSecurity)}</li>
 *         <li>{@link ServerHttpSecurity.FormLoginSpec#configure(ServerHttpSecurity)}</li>
 *     </ul>
 * </p>
 * <p>
 *     {@link AuthenticationWebFilter}为通用{@link WebFilter}， 通过{@link ServerAuthenticationConverter}
 *     转换器生成{@link Authentication}， 如果为{@link Mono#empty()}则执行{@link WebFilterChain}中下一个{@link WebFilter}。
 * </p>
 * <p>
 *     {@link ServerFormLoginAuthenticationConverter}与{@link ServerHttpBasicAuthenticationConverter}
 *     这两个转换器进行基本的{@code username}与{@code password}提取，随后进行认证
 * </p>
 *
 * @author 鬼王
 * @date 2022/03/21 22:18
 */
@Deprecated
public class CryptoAuthenticationWebFilter implements WebFilter {

    private Logger logger = LoggerFactory.getLogger(CryptoAuthenticationWebFilter.class);

    private ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers
            .pathMatchers(HttpMethod.POST, "/auth/login");

    public static CryptoAuthenticationWebFilter of() {
        return new CryptoAuthenticationWebFilter();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //Mono<ServerWebExchangeMatcher.MatchResult> matches = this.matcher.matches(exchange);
        //Mono<ServerWebExchangeMatcher.MatchResult> filter1 = matches.filter(ServerWebExchangeMatcher.MatchResult::isMatch);
        //Mono<ServerWebExchangeMatcher.MatchResult> matchResultMono = filter1.switchIfEmpty(chain.filter(exchange).then(Mono.empty()));
        //matchResultMono.map()
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setBearerAuth();
        //httpHeaders.setBasicAuth();
        //httpHeaders.get


        //return this.matcher.matches(exchange)
        //        .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
        //        .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
        //        .flatMap(matchResult -> authenticate(exchange, chain));

        return this.matcher.matches(exchange)
                .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .flatMap(m -> authenticate(exchange, chain));
    }

    private Mono<Void> authenticate(ServerWebExchange exchange, WebFilterChain chain) {
        logger.info("match /auth/login...");
        RestServerAuthenticationSuccessHandler of = RestServerAuthenticationSuccessHandler.of();
        WebFilterExchange webFilterExchange = new WebFilterExchange(exchange, chain);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "password");
        return of.onAuthenticationSuccess(webFilterExchange, token);
        //ServerHttpResponse result = exchange.getResponse();
        //result.setStatusCode(HttpStatus.OK);
        //result.getHeaders().setContentType(MediaType.TEXT_HTML);
        //return Mono.empty();
    }

}
