package org.thraex.boardroom.entity;

import org.thraex.toolkit.constant.Whether;
import org.thraex.toolkit.entity.Entity;

/**
 * @author 鬼王
 * @date 2021/07/14 18:04
 */
public class Room extends Entity<Room> {

    private String name;

    /**
     * 类型(Dict ID)
     */
    private String typeId;

    /**
     * 地点(Dict ID)
     */
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
    private String projectionTypeId;

    private Whether network;

    private Whether computer;

    /**
     * 扩音
     */
    private Whether pa;

    private Whether restroom;



    private Integer sort;

    private String remark;

}
