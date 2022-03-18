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
    public ResponseResult<List<User>> list(User.Query query) {
        return ResponseResult.ok(service.findAll(query));
    }

    @GetMapping("page")
    public ResponseResult<PageWrapper<User>> page(User.Page page) {
        return ResponseResult.ok(service.findAll(page));
    }

    /**
     * @param identifier id or username
     * @return
     */
    @GetMapping("identifier/{identifier}")
    public ResponseResult<User> one(@PathVariable String identifier) {
        return ResponseResult.ok(service.findByIdentifier(User.of(identifier), "locked"));
    }

    @PostMapping
    public ResponseResult<User> save(@RequestBody User entity) {
        return ResponseResult.ok(service.save(entity));
    }

    @DeleteMapping("{id}")
    public ResponseResult delete(@PathVariable String id) {
        service.repo().deleteById(id);
        return ResponseResult.ok();
    }

}
