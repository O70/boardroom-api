package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.Result;
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
public class DictController {

    private final DictService service;

    public DictController(DictService service) {
        this.service = service;
    }

    @GetMapping
    public Result<List<Dict>> list(Dict.Query query) {
        return Result.ok(service.findAll(query));
    }

    @GetMapping("tree")
    public Result<List<Dict>> tree(Dict.Query query) {
        return Result.ok(Dict.toTree(null, service.findAll(query)));
    }

    /**
     * @param identifier id or code
     * @return
     */
    @GetMapping("{identifier}")
    public Result<Dict> one(@PathVariable String identifier) {
        return Result.ok(service.findByIdentifier(Dict.of(identifier)));
    }

    /**
     * @param identifier id or code
     * @return
     */
    @GetMapping("{identifier}/children")
    public Result<Dict> children(@PathVariable String identifier) {
        Optional<Dict> one = service.findByIdentifier(Dict.of(identifier));
        one.ifPresent(it -> it.setChildren(service.repo().findByParentIdOrderByLevel(it.getId())));

        return Result.ok(one.orElse(null));
    }

    @PostMapping
    public Result<Dict> save(@RequestBody Dict entity) {
        return Result.ok(service.save(entity));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        service.repo().deleteById(id);
        return Result.ok();
    }

}
