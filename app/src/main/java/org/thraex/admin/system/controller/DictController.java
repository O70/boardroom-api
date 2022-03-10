package org.thraex.admin.system.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.Result;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.repository.DictRepository;

import java.util.List;

/**
 * @author 鬼王
 * @date 2022/03/04 18:12
 */
@RestController
@RequestMapping("dict")
public class DictController {

    private final DictRepository repository;

    public DictController(DictRepository repository) {
        this.repository = repository;
    }

    private List<Dict> query() {
        return repository.findAll(Sort.by(Sort.Order.asc("level")));
    }

    @GetMapping
    public Result<List<Dict>> list() {
        return Result.ok(query());
    }

    @GetMapping("tree")
    public Result<List<Dict>> tree() {
        return Result.ok(Dict.toTree(null, query()));
    }

    @GetMapping("{id}")
    public Result<Dict> one(@PathVariable String id) {
        return Result.ok(repository.findById(id).orElse(null));
    }

    @PostMapping
    public Result<Dict> save(@RequestBody Dict dict) {
        return Result.ok(repository.save(dict));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

}
