package org.thraex.admin.system.controller;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.mvc.controller.GenericController;
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
@EnableAsync
public class UserController extends GenericController<User, UserService> {

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
    public ResponseResult<User> orOne(@PathVariable String identifier) {
        return ResponseResult.ok(service.findOneByAny(User.of(identifier), "locked"));
    }

}
