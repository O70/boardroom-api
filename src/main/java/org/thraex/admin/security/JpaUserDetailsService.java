package org.thraex.admin.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.thraex.admin.system.entity.User;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author 鬼王
 * @date 2022/03/22 15:38
 */
public class JpaUserDetailsService implements ReactiveUserDetailsService {

    private static final String USERNAME = "username";

    private final JpaSpecificationExecutor<User> repository;

    public JpaUserDetailsService(JpaSpecificationExecutor repository) {
        this.repository = repository;
    }

    public static JpaUserDetailsService of(JpaSpecificationExecutor<User> repository) {
        return new JpaUserDetailsService(repository);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Optional.ofNullable(username)
                .filter(StringUtils::isNotBlank)
                .map(key -> repository.findOne((root, cq, cb) -> cb.equal(root.get(USERNAME), key)))
                .flatMap(Function.identity())
                .map(this::with)
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    private UserDetails with(User user) {
        String username = user.getUsername();
        String password = String.format("{bcrypt}%s", user.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_" + username.toUpperCase()));

        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                user.isEnabled(),
                user.isExpired(),
                false,
                user.isLocked(),
                authorities
        );
    }

}
