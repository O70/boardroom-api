package org.thraex.boardroom.business.entity;

import org.thraex.boardroom.base.constant.OrderState;
import org.thraex.toolkit.constant.Whether;
import org.thraex.toolkit.entity.JpaEntity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * 预定订单详情
 *
 * @author 鬼王
 * @date 2021/07/15 15:08
 */
@Entity
public class OrderDetail extends JpaEntity<OrderDetail> {

    @ManyToOne
    //@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    ///**
    // * @see {@link Room#getId()}
    // */
    //@Column(length = 36)
    //private String roomId;
    @OneToOne
    //@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Room room;

    public Order getOrder() {
        return order;
    }

    public OrderDetail setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public OrderDetail setRoom(Room room) {
        this.room = room;
        return this;
    }

    /**
     * 会议类型(Dict ID)
     */
    @Column(length = 36)
    private String typeId;

    /**
     * 会议类型名称
     */
    private String typeName;

    /**
     * 是否涉密
     */
    private Whether secret;

    /**
     * 会议人数
     */
    private Integer num;

    /**
     * 院内承办单位ID
     */
    @Column(length = 36)
    private String orgId;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 会议主题
     */
    private String subject;

    /**
     * 会议简介
     */
    private String introduction;

    /**
     * 参与单位
     */
    private String participateUnits;

    /**
     * 院内领导
     */
    private String leaders;

    /**
     * 其他领导
     */
    private String otherLeaders;

    /**
     * 照相
     */
    private String photograph;

    /**
     * 桌卡
     */
    private String tableCard;

    /**
     * 横幅
     */
    private String banner;

    /**
     * 指路牌
     */
    private String signpost;

    /**
     * 摆台纸
     */
    private String paper;

    /**
     * 摆台笔
     */
    private String pen;

    /**
     * 电脑
     */
    private String computer;

    /**
     * 保密
     */
    private String secrecy;

    /**
     * 投影仪
     */
    private String projector;

    private String remark;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private OrderState state;

    //public String getRoomId() {
    //    return roomId;
    //}
    //
    //public OrderDetail setRoomId(String roomId) {
    //    this.roomId = roomId;
    //    return this;
    //}

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Whether getSecret() {
        return secret;
    }

    public void setSecret(Whether secret) {
        this.secret = secret;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getParticipateUnits() {
        return participateUnits;
    }

    public void setParticipateUnits(String participateUnits) {
        this.participateUnits = participateUnits;
    }

    public String getLeaders() {
        return leaders;
    }

    public void setLeaders(String leaders) {
        this.leaders = leaders;
    }

    public String getOtherLeaders() {
        return otherLeaders;
    }

    public void setOtherLeaders(String otherLeaders) {
        this.otherLeaders = otherLeaders;
    }

    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    public String getTableCard() {
        return tableCard;
    }

    public void setTableCard(String tableCard) {
        this.tableCard = tableCard;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getSignpost() {
        return signpost;
    }

    public void setSignpost(String signpost) {
        this.signpost = signpost;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getPen() {
        return pen;
    }

    public void setPen(String pen) {
        this.pen = pen;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getSecrecy() {
        return secrecy;
    }

    public void setSecrecy(String secrecy) {
        this.secrecy = secrecy;
    }

    public String getProjector() {
        return projector;
    }

    public void setProjector(String projector) {
        this.projector = projector;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public OrderState getState() {
        return state;
    }

    public OrderDetail setState(OrderState state) {
        this.state = state;
        return this;
    }

}
