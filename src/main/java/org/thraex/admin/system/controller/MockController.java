package org.thraex.admin.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.generics.response.ResponseResult;
import org.thraex.admin.system.entity.Dict;
import org.thraex.admin.system.entity.Role;
import org.thraex.admin.system.entity.User;
import org.thraex.admin.system.service.RoleService;
import org.thraex.admin.system.service.UserService;

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

    private final RoleService roleService;
    private final UserService userService;

    public MockController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("dict")
    public ResponseResult<List<Dict>> dict() {
        return null;
    }

    @PostMapping("role")
    public ResponseResult<List<Role>> role() {
        List<Role> collect = IntStream.range(0, 100)
                .mapToObj(i -> Role.of().setName("Role-" + i)
                        .setCode("ROLE_MOCK_CODE_" + i).setEnabled(i % 3 == 0).setSort(i))
                .collect(Collectors.toList());
        List<Role> saves = roleService.repo().saveAll(collect);

        return ResponseResult.ok(saves);
    }

    @PostMapping("user")
    public ResponseResult<List<User>> user() {
        List<User> collect = IntStream.range(0, 165)
                .mapToObj(i -> User.of()
                        .setNickname("Nickname" + i)
                        .setUsername("Username-" + i)
                        .setPassword("Password-" + i)
                        .setEmail("Email-" + i)
                        .setMobile("Mobile-" + i)
                        .setEnabled(i % 3 == 0)
                        .setLocked(i % 4 == 0)
                        .setOrgId("OrgId-" + i)
                        .setSort(i)
                )
                .collect(Collectors.toList());
        List<User> saves = userService.repo().saveAll(collect);

        return ResponseResult.ok(saves);
    }

}
