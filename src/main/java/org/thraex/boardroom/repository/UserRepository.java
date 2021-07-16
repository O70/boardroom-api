package org.thraex.boardroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.boardroom.entity.User;

/**
 * @author 鬼王
 * @date 2021/07/16 17:10
 */
public interface UserRepository extends JpaRepository<User, String> {
}
