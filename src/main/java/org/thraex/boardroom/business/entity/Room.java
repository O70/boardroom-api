package org.thraex.boardroom.business.entity;

import org.thraex.toolkit.constant.Whether;
import org.thraex.toolkit.entity.JpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 鬼王
 * @date 2021/07/14 18:04
 */
@Entity
public class Room extends JpaEntity<Room> {

    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    /**
     * 类型(Dict ID)
     */
    @Column(length = 36)
    private String typeId;

    /**
     * 地点(Dict ID)
     */
    @Column(length = 36)
    private String locationId;

    /**
     * 具体位置
     */
    private String address;

    /**
     * 房间号
     */
    private String number;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 投影类型(Dict ID)
     */
    @Column(length = 36)
    private String projectionTypeId;

    /**
     * 有无网络
     */
    private Whether network;

    /**
     * 有无电脑
     */
    private Whether computer;

    /**
     * 有无扩音设备
     */
    private Whether pa;

    /**
     * 是否无纸化办公
     */
    private Whether paperless;

    /**
     * 有无休息间
     */
    private Whether restroom;

    /**
     * 有无独立卫生间
     */
    private Whether toilet;

    /**
     * 是否维护
     */
    private Whether maintain;

    /**
     * 是否预留
     */
    private Whether reserved;

    /**
     * 容纳人数
     */
    private Integer capacity;

    /**
     * 主桌容量
     */
    private Integer tableCapacity;

    private Integer sort;

    private String remark;

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public String getTypeId() {
        return typeId;
    }

    public Room setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public String getLocationId() {
        return locationId;
    }

    public Room setLocationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Room setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Room setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Room setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getProjectionTypeId() {
        return projectionTypeId;
    }

    public Room setProjectionTypeId(String projectionTypeId) {
        this.projectionTypeId = projectionTypeId;
        return this;
    }

    public Whether getNetwork() {
        return network;
    }

    public Room setNetwork(Whether network) {
        this.network = network;
        return this;
    }

    public Whether getComputer() {
        return computer;
    }

    public Room setComputer(Whether computer) {
        this.computer = computer;
        return this;
    }

    public Whether getPa() {
        return pa;
    }

    public Room setPa(Whether pa) {
        this.pa = pa;
        return this;
    }

    public Whether getPaperless() {
        return paperless;
    }

    public Room setPaperless(Whether paperless) {
        this.paperless = paperless;
        return this;
    }

    public Whether getRestroom() {
        return restroom;
    }

    public Room setRestroom(Whether restroom) {
        this.restroom = restroom;
        return this;
    }

    public Whether getToilet() {
        return toilet;
    }

    public Room setToilet(Whether toilet) {
        this.toilet = toilet;
        return this;
    }

    public Whether getMaintain() {
        return maintain;
    }

    public Room setMaintain(Whether maintain) {
        this.maintain = maintain;
        return this;
    }

    public Whether getReserved() {
        return reserved;
    }

    public Room setReserved(Whether reserved) {
        this.reserved = reserved;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Room setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public Integer getTableCapacity() {
        return tableCapacity;
    }

    public Room setTableCapacity(Integer tableCapacity) {
        this.tableCapacity = tableCapacity;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Room setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Room setRemark(String remark) {
        this.remark = remark;
        return this;
    }

}
