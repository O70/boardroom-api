package org.thraex.admin.system.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.thraex.admin.generics.model.AbstractTree;

import javax.persistence.Entity;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/12/27 17:25
 */
@Entity
@SQLDelete(sql = "UPDATE base_dict SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Dict extends AbstractTree<Dict> {

    private String value;

    public String getValue() {
        return value;
    }

    public Dict setValue(String value) {
        this.value = value;
        return this;
    }

    public static Dict of() {
        return new Dict();
    }

    public static Dict of(Query query) {
        return Optional.ofNullable(query)
                .map(q -> {
                    Dict dict = Dict.of()
                            .setId(q.getId())
                            .setName(q.getName())
                            .setCode(q.getCode())
                            .setLevel(q.getLevel());

                    if (StringUtils.isNotBlank(q.getParentId())) {
                        dict.setParent(Dict.of().setId(q.getParentId()));
                    }

                    Optional.ofNullable(q.isEnabled()).ifPresent(dict::setEnabled);

                    return dict;
                }).orElse(Dict.of());
    }

    public class Query {

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Dict#getId()}
         * </p>
         */
        private String id;

        /**
         * Operator: {@code like} contains
         * <p>
         *     Value: {@link Dict#getName()}
         * </p>
         */
        private String name;

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Dict#getCode()}
         * </p>
         */
        private String code;

        /**
         * Operator: {@code like} startsWith
         * <p>
         *     Value: {@link Dict#getLevel()}
         * </p>
         */
        private String level;

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Dict#getParent()} id
         * </p>
         */
        private String parentId;

        /**
         * Operator: {@code equals}
         * <p>
         *     Value: {@link Dict#isEnabled()}
         * </p>
         */
        private Boolean enabled;

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

        public String getLevel() {
            return level;
        }

        public Query setLevel(String level) {
            this.level = level;
            return this;
        }

        public String getParentId() {
            return parentId;
        }

        public Query setParentId(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public Boolean isEnabled() {
            return enabled;
        }

        public Query setEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

    }

}
