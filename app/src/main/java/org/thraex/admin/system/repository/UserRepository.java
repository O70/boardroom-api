package org.thraex.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.admin.system.entity.User;

/**
 * @author 鬼王
 * @date 2022/03/16 18:38
 */
public interface UserRepository extends JpaRepository<User, String> {
}
