package org.thraex.boardroom.business.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.base.constant.BookingType;
import org.thraex.boardroom.base.constant.OrderState;
import org.thraex.toolkit.constant.Whether;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 鬼王
 * @date 2021/07/16 17:14
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User save() {
        User user = new User();
        user
            .setName("鬼王")
            .setPrice(30100.0)
            .setType(BookingType.PERIODIC)
                .setOrderState(OrderState.APPROVED_NON_NEW)
            .setCreateTime(LocalDateTime.now())
            .setDeleted(Whether.NO)
        ;
        User save = userRepository.save(user);
        return save;
    }

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User one(@PathVariable String id) {
        return userRepository.getOne(id);
    }

}
