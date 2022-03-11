package org.thraex.admin.system.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.repository.DictRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/10 13:40
 */
@Service
public class DictService {

    private final DictRepository repository;

    public DictService(DictRepository repository) {
        this.repository = repository;
    }

    public DictRepository repo() {
        return repository;
    }

    public List<Dict> findAll(Dict.Query query) {
        List<String> ignorePaths = new ArrayList<>(2);
        ignorePaths.add("deleted");
        if (Objects.isNull(query) || Objects.isNull(query.getEnabled())) {
            ignorePaths.add("enabled");
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("level", ExampleMatcher.GenericPropertyMatchers.startsWith())
                //.withMatcher("parent", matcher1 -> {
                //    matcher1.transform(new ExampleMatcher.PropertyValueTransformer() {
                //
                //        @Override
                //        public Optional<Object> apply(Optional<Object> o) {
                //            return Optional.empty();
                //        }
                //    });
                //    ExampleMatcher.GenericPropertyMatcher matcher11 = matcher1;
                //    System.out.println(matcher1);
                //})
                .withTransformer("parent", o -> {
                    Optional<Dict> dict = o.map(d -> (Dict) d).flatMap(d -> repository.findById(d.getId()));
                    return dict.map(d -> (Object) d);
                })
                .withIgnorePaths(ignorePaths.toArray(new String[ignorePaths.size()]));
        Example<Dict> example = Example.of(Dict.of(query), matcher);

        return repository.findAll(example, Sort.by(Sort.Order.asc("level")));
    }

    public Optional<Dict> findOne(String identifier) {
        Dict dict = Dict.of().setId(identifier).setCode(identifier);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnorePaths("enabled", "deleted");
        Example<Dict> example = Example.of(dict, matcher);

        return repository.findOne(example);
    }

}
