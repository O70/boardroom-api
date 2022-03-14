package org.thraex.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.admin.system.entity.Role;

/**
 * @author 鬼王
 * @date 2022/03/14 14:01
 */
public interface RoleRepository extends JpaRepository<Role, String> {
}
