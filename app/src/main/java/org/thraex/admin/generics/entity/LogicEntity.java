package org.thraex.admin.generics.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Objects;

/**
 * @author 鬼王
 * @date 2021/12/28 16:23
 */
@MappedSuperclass
public abstract class LogicEntity<T extends LogicEntity<T>> extends JpaEntity<T> {

    @Column(nullable = false)
    private Boolean deleted;

    public Boolean isDeleted() {
        return deleted;
    }

    public T setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return (T) this;
    }

    @PrePersist
    void preDeleted() {
        if (Objects.isNull(deleted)) {
            this.deleted = false;
        }
    }

}
