package org.thraex.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.admin.system.entity.Dict;

import java.util.List;

/**
 * @author 鬼王
 * @date 2022/03/07 14:53
 */
public interface DictRepository extends JpaRepository<Dict, String> {

    /**
     * <pre>
     *     获取系统根字典
     *     配合以下关系使用：
     *     {@code @OneToMany(fetch = FetchType.EAGER)}
     *     {@code @JoinColumn(name = "parent_id")}
     *     {@code private List<T> children;}
     * </pre>
     *
     *
     * @return
     */
    List<Dict> findByParentIsNull();

}
