package org.thraex.admin.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.thraex.admin.system.entity.User;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODO: Opt Authorities
 *
 * @author 鬼王
 * @date 2022/03/22 15:38
 */
public class JpaUserDetailsService implements ReactiveUserDetailsService {

    private final JpaSpecificationExecutor<User> repository;

    public JpaUserDetailsService(JpaSpecificationExecutor<User> repository) {
        Assert.notNull(repository, "repository cannot be null");
        this.repository = repository;
    }

    public static JpaUserDetailsService of(JpaSpecificationExecutor<User> repository) {
        return new JpaUserDetailsService(repository);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Optional.ofNullable(username)
                .filter(StringUtils::isNotBlank)
                .map(key -> repository.findOne((root, cq, cb) -> cb.equal(root.get("username"), key)))
                .flatMap(Function.identity())
                .map(this::with)
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    private UserDetails with(User user) {
        // TODO: Authorities
        String password = String.format("{bcrypt}%s", user.getPassword());
        return user.setPassword(password);
    }

}
