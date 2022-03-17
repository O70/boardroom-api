package org.thraex.admin.generics.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.thraex.admin.generics.entity.SoftEntity;
import org.thraex.admin.generics.util.Mixins;

import java.util.function.Supplier;

/**
 * TODO: feat
 *
 * @author 鬼王
 * @date 2022/03/16 18:43
 */
public class GenericService<T extends SoftEntity<T>, R extends JpaRepositoryImplementation<T, String>> {

    //@Autowired
    //private R repository;
    private final R repository;

    public GenericService(R repository) {
        this.repository = repository;
    }

    public R repo() {
        return repository;
    }

    public T save(T entity) {
        String id = entity.getId();

        Supplier<T> from = () -> {
            T old = repository.findById(id).orElseThrow(Mixins.updateException(id));
            BeanUtils.copyProperties(entity, old, Mixins.IGNORE_UPDATE_FIELDS);

            return old;
        };

        T edit = StringUtils.isBlank(id) ? entity : from.get();

        return repository.save(edit);
    }

}
