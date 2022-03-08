package org.thraex.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.admin.system.entity.Dict;

/**
 * @author 鬼王
 * @date 2022/03/07 14:53
 */
public interface DictRepository extends JpaRepository<Dict, String> {
}
