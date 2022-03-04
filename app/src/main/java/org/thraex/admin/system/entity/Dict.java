package org.thraex.admin.system.entity;

import org.thraex.admin.generics.entity.AbstractTree;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

/**
 * @author 鬼王
 * @date 2021/12/27 17:25
 */
@Entity
public class Dict extends AbstractTree<Dict> {

    private String value;

    //@OneToMany
    //@JoinColumn(name = "parent_id")
    //private List<Dict> children;

    public String getValue() {
        return value;
    }

    public Dict setValue(String value) {
        this.value = value;
        return this;
    }

    //@Override
    //public Collection<Dict> children() {
    //    return null;
    //}

}
