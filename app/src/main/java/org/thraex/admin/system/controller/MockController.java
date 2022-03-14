package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.Result;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.entity.Role;
import org.thraex.admin.system.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 鬼王
 * @date 2022/03/14 16:42
 */
@RestController
@RequestMapping("mock")
public class MockController {

    private final RoleService service;

    public MockController(RoleService service) {
        this.service = service;
    }

    @PostMapping("dict")
    public Result<List<Dict>> dict() {
        return null;
    }

    @PostMapping("role")
    public Result<List<Role>> role() {
        List<Role> collect = IntStream.range(0, 100)
                .mapToObj(i -> Role.of().setName("Role-" + i)
                        .setCode("ROLE_MOCK_CODE_" + i).setEnabled(i % 3 == 0).setSort(i))
                .collect(Collectors.toList());
        List<Role> roles = service.repo().saveAll(collect);

        return Result.ok(roles);
    }

}
