package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping
    public List<Dict> list() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Dict one(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Dict save(@RequestBody Dict dict) {
        return repository.save(dict);
    }

    @PostMapping("batch")
    public List<Dict> batch(List<Dict> dicts) {
        return repository.saveAll(dicts);
    }

    @PutMapping
    public Dict update(@RequestBody Dict dict) {
        return null;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

}