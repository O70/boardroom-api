package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.Result;
import org.thraex.admin.system.entity.Role;
import org.thraex.admin.system.service.RoleService;

import java.util.List;

/**
 * @author 鬼王
 * @date 2022/03/14 14:00
 */
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public Result<List<Role>> list(Role query) {
        return Result.ok(service.repo().findAll());
    }

    @GetMapping("page")
    public Result<List<Role>> page(Role query) {
        return Result.ok(null);
    }

    /**
     * @param identifier id or code
     * @return
     */
    @GetMapping("{identifier}")
    public Result<Role> one(@PathVariable String identifier) {
        return Result.ok(service.findOne(identifier).orElse(null));
    }

    @PostMapping
    public Result<Role> save(@RequestBody Role entity) {
        return Result.ok(service.save(entity));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.repo().deleteById(id);
    }

}
