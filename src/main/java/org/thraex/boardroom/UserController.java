package org.thraex.boardroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.entity.User;
import org.thraex.boardroom.repository.UserRepository;

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
                //.setId("id-0")
                .setName("鬼王").setPrice(30100.0);
        User save = userRepository.save(user);
        return save;
    }

}
