package org.thraex.admin.generics.model;

import org.thraex.admin.generics.entity.LogicEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author 鬼王
 * @date 2021/12/31 17:51
 */
@MappedSuperclass
public abstract class AbstractTree<T extends AbstractTree<T>>
        extends LogicEntity<T> implements Serializable {

    private String name;

    @Column(unique = true)
    private String code;

    private String level;

    @ManyToOne
    private T parent;

    private boolean enabled;

    private String remark;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private Collection<T> children;

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getCode() {
        return code;
    }

    public T setCode(String code) {
        this.code = code;
        return (T) this;
    }

    public String getLevel() {
        return level;
    }

    public T setLevel(String level) {
        this.level = level;
        return (T) this;
    }

    public T getParent() {
        return parent;
    }

    public T setParent(T parent) {
        this.parent = parent;
        return (T) this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public T setEnabled(boolean enabled) {
        this.enabled = enabled;
        return (T) this;
    }

    public String getRemark() {
        return remark;
    }

    public T setRemark(String remark) {
        this.remark = remark;
        return (T) this;
    }

    public Collection<T> getChildren() {
        return children;
    }

    public T setChildren(List<T> children) {
        this.children = children;
        return (T) this;
    }

}
