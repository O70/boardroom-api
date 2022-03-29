package org.thraex.admin.system.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thraex.admin.generics.constant.EntityMixins;
import org.thraex.admin.generics.model.AbstractAccount;
import org.thraex.admin.generics.page.PageQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO: Opt Authorities
 *
 * @author 鬼王
 * @date 2022/03/04 19:07
 */
@Entity
@SQLDelete(sql = EntityMixins.SOFT_DELETE_USER)
@Where(clause = EntityMixins.WHERE_CLAUSE)
public class User extends AbstractAccount<User> implements UserDetails {

    @Column(length = 36)
    private String orgId;

    private Integer sort;

    public String getOrgId() {
        return orgId;
    }

    public User setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public User setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public static User of() {
        return new User();
    }

    public static User of(String identifier) {
        return of().setId(identifier).setUsername(identifier);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: Add role
        List<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_MOCK_" + getUsername().toUpperCase()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public class Query implements Serializable {

        private String keyword;

        private String orgId;

        private Boolean enabled;

        private Boolean locked;

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

        public Boolean getLocked() {
            return locked;
        }

        public Query setLocked(Boolean locked) {
            this.locked = locked;
            return this;
        }

    }

    public class Page extends PageQuery {

        private Query params = new Query();

        public Query getParams() {
            return params;
        }

        public Page setParams(Query params) {
            this.params = params;
            return this;
        }

    }

}
