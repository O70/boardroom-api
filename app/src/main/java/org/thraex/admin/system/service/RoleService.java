package org.thraex.admin.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thraex.admin.generics.util.Mixins;
import org.thraex.admin.system.entity.Role;
import org.thraex.admin.system.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

/**
 * <pre>
 *     TODO:
 *     - Opt: QBE to Specifications
 * </pre>
 *
 * @author 鬼王
 * @date 2022/03/14 14:02
 */
@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public RoleRepository repo() {
        return repository;
    }

    public List<Role> findAll(Role.Query query) {
        return repository.findAll(example(query), Sort.by("sort"));
    }

    public Page<Role> findAll(Role.Page page) {
        Role.Query query = page.getQuery();
        PageRequest pageable = PageRequest.of(page.getPage(), page.getSize(), Sort.by("sort"));

        return repository.findAll(example(query), pageable);
    }

    private Example<Role> example(Role.Query query) {
        ExampleMatcher basic = ExampleMatcher.matching()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("remark", contains())
                .withIgnorePaths("deleted");

        ExampleMatcher matcher = Optional.ofNullable(query)
                .map(Role.Query::getEnabled)
                .map(it -> basic)
                .orElse(basic.withIgnorePaths("enabled"));

        return Example.of(Role.of(query), matcher);
    }

    public Optional<Role> findOne(String identifier) {
        Role probe = Role.of(identifier);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths("enabled", "deleted");
        Example<Role> example = Example.of(probe, matcher);

        return repository.findOne(example);
    }

    /**
     * TODO: Opt update
     *
     * @param entity
     * @return
     */
    public Role save(Role entity) {
        String id = entity.getId();

        Supplier<Role> from = () -> {
            Role old = repository.findById(id).orElseThrow(Mixins.updateException(id));
            BeanUtils.copyProperties(entity, old, Mixins.IGNORE_UPDATE_FIELDS);

            return old;
        };

        Role edit = StringUtils.isBlank(id) ? entity : from.get();

        return repository.save(edit);
    }

}
