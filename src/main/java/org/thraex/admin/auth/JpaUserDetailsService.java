package org.thraex.admin.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.repository.UserRepository;
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

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Optional.ofNullable(username)
                .filter(StringUtils::isNotBlank)
                .map(key -> userRepository.findOne((root, cq, cb) -> cb.equal(root.get(USERNAME), key)))
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
