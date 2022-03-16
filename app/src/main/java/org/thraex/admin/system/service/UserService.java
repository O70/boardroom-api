package org.thraex.admin.system.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/16 18:39
 */
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserRepository repo() {
        return repository;
    }

    public List<User> findAll(User.Query query) {
        return null;
    }

    public Page<User> findAll(User.Page page) {
        return null;
    }

    public Optional<User> findOne(String identifier) {
        User probe = User.of(identifier);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths("enabled", "deleted");
        Example<User> example = Example.of(probe, matcher);

        return repository.findOne(example);
    }

    /**
     * TODO: Opt update
     *
     * @param entity
     * @return
     */
    public User save(User entity) {
        return null;
    }

}
