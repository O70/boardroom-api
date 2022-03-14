package org.thraex.admin.generics.entity;

import javax.persistence.MappedSuperclass;

/**
 * @author 鬼王
 * @date 2021/12/28 16:23
 */
@MappedSuperclass
public abstract class LogicEntity<T extends LogicEntity<T>> extends JpaEntity<T> {

    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public T setDeleted(boolean deleted) {
        this.deleted = deleted;
        return (T) this;
    }

}
