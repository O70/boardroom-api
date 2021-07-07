package org.thraex.base.entity;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2021/07/06 17:05
 */
public class Entity implements Serializable {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
