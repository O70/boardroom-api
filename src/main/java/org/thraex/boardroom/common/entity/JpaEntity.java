package org.thraex.boardroom.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 鬼王
 * @date 2021/07/10 16:46
 */
@MappedSuperclass
public class JpaEntity<E extends JpaEntity<E>> implements Serializable {

    @Id
    @Column(length = 36)
    @GeneratedValue(generator= "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
    private String id;

    @Column(length = 36)
    private String createBy;

    private LocalDateTime createTime;

    @Column(length = 36)
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