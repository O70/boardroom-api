package test;

import org.thraex.boardroom.base.constant.BookingType;
import org.thraex.boardroom.base.constant.OrderState;
import org.thraex.toolkit.constant.Whether;
import org.thraex.toolkit.entity.JpaEntity;

import javax.persistence.Entity;

/**
 * @author 鬼王
 * @date 2021/07/16 15:58
 */
@Entity
public class User extends JpaEntity<User> {

    private String name;

    private Integer sex;

    private Integer age;

    private Double price;

    private BookingType type;

    private OrderState orderState;

    private Whether deleted;

    private String remark;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public User setPrice(Double price) {
        this.price = price;
        return this;
    }

    public BookingType getType() {
        return type;
    }

    public User setType(BookingType type) {
        this.type = type;
        return this;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public User setOrderState(OrderState orderState) {
        this.orderState = orderState;
        return this;
    }

    public Whether getDeleted() {
        return deleted;
    }

    public User setDeleted(Whether deleted) {
        this.deleted = deleted;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public User setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    /*@PostLoad
    void fillTransient() {
        this.type = BookingType.of(this.typeValue);
    }

    @PrePersist
    void fillPersistent() {
        if (this.type != null) {
            this.typeValue = this.type.value();
        }
    }*/

}
