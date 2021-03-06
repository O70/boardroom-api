package org.thraex.admin.generics.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thraex.admin.generics.entity.SoftEntity;
import org.thraex.admin.generics.mvc.service.GenericService;
import org.thraex.admin.generics.response.ResponseResult;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2022/03/18 13:51
 */
public class GenericController<T extends SoftEntity<T>, S extends GenericService<T, ?>> {

    @Autowired
    protected S service;

    @GetMapping("{id}")
    public ResponseResult<T> one(@PathVariable String id) {
        Optional<T> one = service.repo().findById(id);
        return ResponseResult.ok(one);
    }

    @PostMapping
    public ResponseResult<T> save(@RequestBody T entity) {
        T saved = service.save(entity);
        return ResponseResult.ok(saved);
    }

    @PutMapping
    public ResponseResult<T> update(@RequestBody T entity) {
        Assert.notNull(entity.getId(), "The given entity id must not be null!");
        return save(entity);
    }

    @DeleteMapping("{id}")
    public ResponseResult delete(@PathVariable String id) {
        service.repo().deleteById(id);
        return ResponseResult.ok();
    }

}
