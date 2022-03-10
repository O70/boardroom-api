package org.thraex.admin.system.entity;

import org.thraex.admin.generics.model.AbstractTree;

import javax.persistence.Entity;

/**
 * @author 鬼王
 * @date 2021/12/27 17:25
 */
@Entity
public class Dict extends AbstractTree<Dict> {

    private String value;

    public String getValue() {
        return value;
    }

    public Dict setValue(String value) {
        this.value = value;
        return this;
    }

    public static Dict of() {
        return new Dict();
    }

}
