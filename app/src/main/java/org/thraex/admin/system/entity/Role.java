package org.thraex.admin.system.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.thraex.admin.generics.entity.LogicEntity;
import org.thraex.admin.generics.page.PageQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Optional;

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

    public static Role of(Query query) {
        Assert.notNull(query, "query must not be null.");

        Role target = Role.of();
        BeanUtils.copyProperties(query, target, "enabled");

        Optional.ofNullable(query.getEnabled()).ifPresent(target::setEnabled);

        return target;
    }

    public class Query implements Serializable {

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Role#getId()}
         * </p>
         */
        private String id;

        /**
         * Operator: {@code like} contains
         * <p>
         *     Value: {@link Role#getName()}
         * </p>
         */
        private String name;

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Role#getCode()}
         * </p>
         */
        private String code;

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Role#isEnabled()}
         * </p>
         */
        private Boolean enabled;

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Role#getSort()}
         * </p>
         */
        private Integer sort;

        /**
         * Operator: {@code like} contains
         * <p>
         *     Value: {@link Role#getRemark()}
         * </p>
         */
        private String remark;

        public String getId() {
            return id;
        }

        public Query setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Query setName(String name) {
            this.name = name;
            return this;
        }

        public String getCode() {
            return code;
        }

        public Query setCode(String code) {
            this.code = code;
            return this;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public Query setEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Integer getSort() {
            return sort;
        }

        public Query setSort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public String getRemark() {
            return remark;
        }

        public Query setRemark(String remark) {
            this.remark = remark;
            return this;
        }

    }

    public class Page extends PageQuery {

        private Query query = new Query();

        public Query getQuery() {
            return query;
        }

        public Page setQuery(Query query) {
            this.query = query;
            return this;
        }

    }

}
