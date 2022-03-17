package org.thraex.admin.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2022/03/16 18:39
 */
@Service
public class UserService {

    private static final Stream<String> stream = Stream.of("nickname", "username", "email", "mobile");
    private static final String PERCENT_SIGN = "%";

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserRepository repo() {
        return repository;
    }

    public List<User> findAll(User.Query query) {
        return repository.findAll(specification(query), Sort.by("sort"));
    }

    public Page<User> findAll(User.Page page) {
        User.Query query = page.getQuery();
        PageRequest pageable = PageRequest.of(page.getPage(), page.getSize(), Sort.by("sort"));

        return repository.findAll(specification(query), pageable);
    }

    private Specification<User> specification(User.Query query) {
        return (root, cq, cb) -> {
            Optional<Predicate> keyword = Optional.ofNullable(query.getKeyword())
                    .filter(StringUtils::isNotBlank)
                    .map(v -> String.format("%s%s%s", PERCENT_SIGN, v, PERCENT_SIGN))
                    .map(v -> stream.map(p -> cb.like(root.get(p), v)))
                    .map(p -> cb.or(p.toArray(Predicate[]::new)));

            Predicate[] predicates = Stream.of(
                    keyword,
                    Optional.ofNullable(query.getOrgId()).map(v -> cb.equal(root.get("orgId"), v)),
                    Optional.ofNullable(query.getEnabled()).map(v -> cb.equal(root.get("enabled"), v)),
                    Optional.ofNullable(query.getLocked()).map(v -> cb.equal(root.get("locked"), v))
            ).filter(Optional::isPresent).toArray(Predicate[]::new);

            return cq.where(predicates).getRestriction();
        };
    }

    public Optional<User> findOne(String identifier) {
        User probe = User.of(identifier);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths("enabled", "deleted", "locked");
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
        String id = entity.getId();

        Supplier<User> from = () -> {
            User old = repository.findById(id).orElseThrow(() ->
                    new EmptyResultDataAccessException(String.format("Target does not exist: [%s]", id), 1));
            String[] ignore = Stream.of(
                    "id",
                    "deleted",
                    "createdBy",
                    "createdDate",
                    "modifiedBy",
                    "modifiedDate"
            ).toArray(String[]::new);
            BeanUtils.copyProperties(entity, old, ignore);

            return old;
        };

        User edit = StringUtils.isBlank(id) ? entity : from.get();

        return repository.save(edit);
    }

}