package org.thraex.admin.system.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.repository.UserRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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
        final String like = "%";
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Optional<Predicate> keyword = Optional.ofNullable(query.getKeyword())
                        .map(v -> String.format("%s%s%s", like, v, like))
                        .map(v -> Stream.of(
                                cb.like(root.get("nickname"), v),
                                cb.like(root.get("username"), v),
                                cb.like(root.get("email"), v),
                                cb.like(root.get("mobile"), v)
                        )).map(p -> cb.or(p.toArray(Predicate[]::new)));


                Stream<Predicate> stream = Stream.of(
                        Optional.ofNullable(query.getOrgId()).map(v -> cb.equal(root.get("orgId"), v)),
                        Optional.ofNullable(query.getEnabled()).map(v -> cb.equal(root.get("enabled"), v)),
                        Optional.ofNullable(query.getLocked()).map(v -> cb.equal(root.get("locked"), v)),
                        keyword
                ).map(p -> p.orElse(null));

                //Predicate[] predicates = Stream.concat(stream1, stream2).filter(Objects::nonNull).toArray(Predicate[]::new);
                Predicate[] predicates = stream.filter(Objects::nonNull).toArray(Predicate[]::new);
                CriteriaQuery<?> where = cq.where(predicates);
                Predicate predicate = where.getRestriction();

                return predicate;
            }
        };

        //Specification<User> or = specification.and(null).or(null);
        List<User> list = repository.findAll(specification, Sort.by("sort"));

        return list;
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
