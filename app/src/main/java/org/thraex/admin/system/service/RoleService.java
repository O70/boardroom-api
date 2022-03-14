package org.thraex.admin.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.Role;
import org.thraex.admin.system.repository.RoleRepository;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
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

    public Optional<Role> findOne(String identifier) {
        Role probe = Role.of(identifier);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths("enabled", "deleted");
        Example<Role> example = Example.of(probe, matcher);

        return repository.findOne(example);
    }

    public Role save(Role entity) {
        String id = entity.getId();

        Supplier<Role> from = () -> {
            Role old = repository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException(String.format("Target does not exist: [%s]", id)));
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

        Role edit = StringUtils.isBlank(id) ? entity : from.get();

        return repository.save(edit);
    }

}
