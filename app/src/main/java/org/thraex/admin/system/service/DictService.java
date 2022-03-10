package org.thraex.admin.system.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.repository.DictRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/10 13:40
 */
@Service
public class DictService {

    private static final String[] IGNORE_PATHS = { "enabled", "deleted" };

    private final DictRepository repository;

    public DictService(DictRepository repository) {
        this.repository = repository;
    }

    public DictRepository repo() {
        return repository;
    }

    public List<Dict> findAll(Dict dict) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths(IGNORE_PATHS);
        Example<Dict> example = Example.of(dict, matcher);

        return repository.findAll(example, Sort.by(Sort.Order.asc("level")));
    }

    public Optional<Dict> findOne(String identifier) {
        Dict dict = Dict.of().setId(identifier).setCode(identifier);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths(IGNORE_PATHS);
        Example<Dict> example = Example.of(dict, matcher);

        return repository.findOne(example);
    }

}
