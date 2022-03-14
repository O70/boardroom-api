package org.thraex.admin.system.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.thraex.admin.generics.entity.LogicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author 鬼王
 * @date 2022/03/04 19:02
 */
@Entity
@SQLDelete(sql = "UPDATE base_role SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Role extends LogicEntity<Role> {

    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    private boolean enabled;

    private Integer sort;

    private String remark;

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Role setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Role setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Role setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Role setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public static Role of() {
        return new Role();
    }

    public static Role of(String identifier) {
        return of().setId(identifier).setCode(identifier);
    }

}
