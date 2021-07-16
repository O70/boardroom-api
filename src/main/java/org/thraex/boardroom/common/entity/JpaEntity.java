package org.thraex.boardroom.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 鬼王
 * @date 2021/07/16 16:46
 */
@MappedSuperclass
public class JpaEntity<E extends JpaEntity<?>> implements Serializable {

    @Id
    //@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    //@GeneratedValue(generator= "uuidGenerator")
    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public E setId(String id) {
        this.id = id;
        return (E) this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public E setCreateBy(String createBy) {
        this.createBy = createBy;
        return (E) this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public E setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return (E) this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public E setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return (E) this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public E setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return (E) this;
    }

}
