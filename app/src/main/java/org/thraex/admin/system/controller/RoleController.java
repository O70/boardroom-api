package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.PageWrapper;
import org.thraex.admin.generics.response.ResponseResult;
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
    public ResponseResult<List<Role>> list(Role.Query query) {
        return ResponseResult.ok(service.findAll(query));
    }

    @GetMapping("page")
    public ResponseResult<PageWrapper<Role>> page(Role.Page page) {
        return ResponseResult.ok(service.findAll(page));
    }

    /**
     * @param identifier id or code
     * @return
     */
    @GetMapping("identifier/{identifier}")
    public ResponseResult<Role> one(@PathVariable String identifier) {
        return ResponseResult.ok(service.findByIdentifier(Role.of(identifier)));
    }

    @PostMapping
    public ResponseResult<Role> save(@RequestBody Role entity) {
        return ResponseResult.ok(service.save(entity));
    }

    @DeleteMapping("{id}")
    public ResponseResult delete(@PathVariable String id) {
        service.repo().deleteById(id);
        return ResponseResult.ok();
    }

}
