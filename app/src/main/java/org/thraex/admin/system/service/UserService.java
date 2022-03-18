package org.thraex.admin.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thraex.admin.generics.mvc.service.GenericService;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2022/03/16 18:39
 */
@Service
public class UserService extends GenericService<User, UserRepository> {

    private static final String[] LIKE_FIELDS = { "nickname", "username", "email", "mobile" };
    private static final String PERCENT_SIGN = "%";

    public List<User> findAll(User.Query query) {
        return repository.findAll(specification(query), Sort.by("sort"));
    }

    public Page<User> findAll(User.Page page) {
        User.Query query = page.getParams();
        PageRequest pageable = PageRequest.of(page.getPage(), page.getSize(), Sort.by("sort"));

        return repository.findAll(specification(query), pageable);
    }

    private Specification<User> specification(User.Query query) {
        return (root, cq, cb) -> {
            Optional<Predicate> keyword = Optional.ofNullable(query.getKeyword())
                    .filter(StringUtils::isNotBlank)
                    .map(v -> String.format("%s%s%s", PERCENT_SIGN, v, PERCENT_SIGN))
                    .map(v -> Stream.of(LIKE_FIELDS).map(p -> cb.like(root.get(p), v)))
                    .map(p -> cb.or(p.toArray(Predicate[]::new)));

            Predicate[] predicates = Stream.of(
                    keyword,
                    Optional.ofNullable(query.getOrgId()).map(v -> cb.equal(root.get("orgId"), v)),
                    Optional.ofNullable(query.getEnabled()).map(v -> cb.equal(root.get("enabled"), v)),
                    Optional.ofNullable(query.getLocked()).map(v -> cb.equal(root.get("locked"), v))
            ).map(p -> p.orElse(null)).filter(Objects::nonNull).toArray(Predicate[]::new);

            return cq.where(predicates).getRestriction();
        };
    }

}
