package org.thraex.admin.generics.mvc.service;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.util.Assert;
import org.thraex.admin.generics.entity.SoftEntity;
import org.thraex.admin.generics.util.Mixins;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * TODO: feat
 *
 * @author 鬼王
 * @date 2022/03/16 18:43
 */
public class GenericService<T extends SoftEntity<T>, R extends JpaRepositoryImplementation<T, String>> {

    @Autowired
    protected R repository;

    public R repo() {
        return repository;
    }

    public Optional<T> findByIdentifier(T probe, String... ignoredPaths) {
        String[] paths = ArrayUtils.addAll(Mixins.IGNORED_PATHS_FIND, ignoredPaths);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths(paths);
        Example<T> example = Example.of(probe, matcher);

        return repository.findOne(example);
    }

    /**
     * {@code <S extends T>}
     *
     * @param entity
     * @return
     */
    public T save(T entity) {
        Assert.notNull(entity, "The given entity must not be null!");

        String id = entity.getId();

        Supplier<T> from = () -> {
            T old = repository.findById(id).orElseThrow(() ->
                    new EmptyResultDataAccessException(String.format("Target does not exist: [%s]", id), 1));
            BeanUtils.copyProperties(entity, old, Mixins.IGNORED_PATHS_UPDATE);

            return old;
        };

        T edit = StringUtils.isBlank(id) ? entity : from.get();

        return repository.save(edit);
    }

}
