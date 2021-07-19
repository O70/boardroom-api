package org.thraex.boardroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.common.constant.ApprovalStatus;
import org.thraex.boardroom.common.constant.BookingType;
import org.thraex.boardroom.common.constant.OrderStatus;
import org.thraex.boardroom.entity.User;
import org.thraex.boardroom.repository.UserRepository;

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
                .setApprovalStatus(ApprovalStatus.APPROVAL)
                .setOrderStatus(OrderStatus.NON_NEWEST)
            .setCreateTime(LocalDateTime.now())
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
