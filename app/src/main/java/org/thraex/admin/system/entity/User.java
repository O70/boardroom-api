package org.thraex.admin.system.entity;

import org.thraex.admin.generics.model.AbstractAccount;
import org.thraex.admin.generics.page.PageQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

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

    public static User of() {
        return new User();
    }

    public static User of(String identifier) {
        return of().setId(identifier).setUsername(identifier);
    }

    public class Query implements Serializable {

        private String keyword;

        private String orgId;

        private Boolean enabled;

        public String getKeyword() {
            return keyword;
        }

        public Query setKeyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public String getOrgId() {
            return orgId;
        }

        public Query setOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public Query setEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

    }

    public class Page extends PageQuery {

        private Query query = new Query();

        public Query getQuery() {
            return query;
        }

        public Page setQuery(Query query) {
            this.query = query;
            return this;
        }

    }

}
