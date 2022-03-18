package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.mvc.controller.GenericController;
import org.thraex.admin.generics.response.ResponseResult;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.service.DictService;

import java.util.List;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/04 18:12
 */
@RestController
@RequestMapping("dict")
public class DictController extends GenericController<Dict, DictService> {

    @GetMapping
    public ResponseResult<List<Dict>> list(Dict.Query query) {
        return ResponseResult.ok(service.findAll(query));
    }

    @GetMapping("tree")
    public ResponseResult<List<Dict>> tree(Dict.Query query) {
        return ResponseResult.ok(Dict.toTree(null, service.findAll(query)));
    }

    /**
     * @param identifier id or code
     * @return
     */
    @GetMapping("identifier/{identifier}")
    public ResponseResult<Dict> orOne(@PathVariable String identifier) {
        return ResponseResult.ok(service.findOneByAny(Dict.of(identifier)));
    }

    /**
     * @param identifier id or code
     * @return
     */
    @GetMapping("{identifier}/children")
    public ResponseResult<Dict> children(@PathVariable String identifier) {
        Optional<Dict> one = service.findOneByAny(Dict.of(identifier));
        one.ifPresent(it -> it.setChildren(service.repo().findByParentIdOrderByLevel(it.getId())));

        return ResponseResult.ok(one.orElse(null));
    }

}
