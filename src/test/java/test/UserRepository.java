package test;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 鬼王
 * @date 2021/07/16 17:10
 */
public interface UserRepository extends JpaRepository<User, String> {
}
