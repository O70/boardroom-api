package org.thraex.admin.system.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thraex.admin.generics.mvc.service.GenericService;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.repository.DictRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * <pre>
 *     TODO:
 *     - Opt: QBE to Specifications
 * </pre>
 * @author 鬼王
 * @date 2022/03/10 13:40
 */
@Service
public class DictService extends GenericService<Dict, DictRepository> {

    public List<Dict> findAll(Dict.Query query) {
        ExampleMatcher basic = ExampleMatcher.matching()
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
                .withTransformer("parent", o -> {
                    Optional<Dict> dict = o.map(d -> (Dict) d).flatMap(d -> repository.findById(d.getId()));
                    return dict.map(d -> (Object) d);
                }).withIgnorePaths("deleted");

        ExampleMatcher matcher = Optional.ofNullable(query)
                .map(Dict.Query::getEnabled)
                .map(it -> basic)
                .orElse(basic.withIgnorePaths("enabled"));

        Example<Dict> example = Example.of(Dict.of(query), matcher);

        return repository.findAll(example, Sort.by("level"));
    }

}
