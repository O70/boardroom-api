package org.thraex.admin.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.repository.DictRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

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
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("level", startsWith())
                /*.withMatcher("parent", matcher1 -> {
                    matcher1.transform(new ExampleMatcher.PropertyValueTransformer() {

                        @Override
                        public Optional<Object> apply(Optional<Object> o) {
                            return Optional.empty();
                        }
                    });
                    ExampleMatcher.GenericPropertyMatcher matcher11 = matcher1;
                    System.out.println(matcher1);
                })*/
                /*.withTransformer("parent", o -> {
                    Optional<Dict> dict = o.map(d -> (Dict) d).flatMap(d -> repository.findById(d.getId()));
                    //Optional<Optional<Dict>> dict1 = Optional.of(dict);
                    //Optional<Dict> dict2 = dict1.flatMap(Function.identity());
                    return Optional.of(dict); //.map(d -> (Object) d);
                })*/;

        /*ExampleMatcher matcher = Optional.ofNullable(query)
                .map(Dict.Query::isEnabled)
                .map(it -> basic)
                .orElse(basic.withIgnorePaths("enabled"));*/

        Example<Dict> example = Example.of(Dict.of(query), matcher);

        return repository.findAll(example, Sort.by(Sort.Order.asc("level")));
    }

    public Optional<Dict> findOne(String identifier) {
        Dict dict = Dict.of().setId(identifier).setCode(identifier);
        Example<Dict> example = Example.of(dict, ExampleMatcher.matchingAny());

        return repository.findOne(example);
    }

    public Dict save(Dict dict) {
        String id = dict.getId();

        Supplier<Dict> from = () -> {
            Dict old = repository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException(String.format("Target does not exist: [%s]", id)));
            String[] ignore = Stream.of(
                    "id",
                    "deleted",
                    "createdBy",
                    "createdDate",
                    "modifiedBy",
                    "modifiedDate"
            ).toArray(String[]::new);
            BeanUtils.copyProperties(dict, old, ignore);

            return old;
        };

        Dict edit = StringUtils.isBlank(id) ? dict : from.get();

        return repository.save(edit);
    }

}
