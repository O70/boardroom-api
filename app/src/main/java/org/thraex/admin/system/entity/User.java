package org.thraex.admin.system.entity;

import org.thraex.admin.generics.model.AbstractAccount;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author 鬼王
 * @date 2022/03/04 19:07
 */
@Entity
public class User extends AbstractAccount<User> {

    @Column(length = 36)
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public User setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

}
