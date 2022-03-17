package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.PageWrapper;
import org.thraex.admin.generics.response.Result;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.service.UserService;

import java.util.List;

/**
 * @author 鬼王
 * @date 2022/03/16 18:46
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Result<List<User>> list(User.Query query) {
        return Result.ok(service.findAll(query));
    }

    @GetMapping("page")
    public Result<PageWrapper> page(User.Page page) {
        return Result.ok(service.findAll(page));
    }

    /**
     * @param identifier id or username
     * @return
     */
    @GetMapping("{identifier}")
    public Result<User> one(@PathVariable String identifier) {
        return Result.ok(service.findOne(identifier));
    }

    @PostMapping
    public Result<User> save(@RequestBody User entity) {
        return Result.ok(service.save(entity));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        service.repo().deleteById(id);
        return Result.ok();
    }

}
