package org.thraex.admin.auth;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2022/03/22 11:33
 */
@Service
public class CryptoReactiveUserDetailsService implements ReactiveUserDetailsService {

    private static final Pattern PASSWORD_ALGORITHM_PATTERN = Pattern.compile("^\\{.+}.*$");

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private Function<String, String> encoder = passwordEncoder::encode;

    private final Map<String, UserDetails> users;

    public CryptoReactiveUserDetailsService(SecurityProperties properties) {
        SecurityProperties.User user = properties.getUser();

        String password = user.getPassword();
        UserDetails userDetails = User.withUsername(user.getName())
                .password(password)
                .roles(user.getRoles().toArray(new String[0]))
                .passwordEncoder(PASSWORD_ALGORITHM_PATTERN.matcher(password).matches()
                        ? Function.identity() : encoder)
                .build();

        UserDetails mutable = User.withUsername("hanzo")
                .password("hanzo")
                .roles("ADMIN")
                .passwordEncoder(encoder)
                .build();

        this.users = Stream.of(userDetails, mutable)
                .collect(Collectors.toConcurrentMap(u -> getKey(u.getUsername()), Function.identity()));
    }

    private String getKey(String username) {
        return username.toLowerCase();
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        String key = getKey(username);

        return Optional.of(key)
                .map(users::get)
                .map(u -> Mono.just(User.withUserDetails(u).build()))
                .orElse(Mono.empty());
    }

}
