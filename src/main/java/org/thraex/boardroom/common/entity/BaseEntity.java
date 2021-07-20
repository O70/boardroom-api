package org.thraex.boardroom.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 鬼王
 * @date 2021/07/16 16:19
 */
@MappedSuperclass
public class BaseEntity <E extends BaseEntity<E>> implements Serializable {

    @Id
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @GeneratedValue(generator= "uuidGenerator")
    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public BaseEntity<E> setId(String id) {
        this.id = id;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public BaseEntity<E> setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public BaseEntity<E> setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public BaseEntity<E> setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public BaseEntity<E> setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
