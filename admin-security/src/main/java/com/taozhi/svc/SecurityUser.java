package com.taozhi.svc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.taozhi.dto.SysUser;

public class SecurityUser implements UserDetails {

    /**
     * Comment for <code>serialVersionUID</code>
     */

    private String userName;
    private String password;
    private List<String> roleList;

    public SecurityUser(SysUser suser, List<String> roleList) {
        this.userName = suser.getUsername();
        this.password = suser.getPassword();
        this.roleList = roleList;
    }

    private static final long serialVersionUID = 6777332941144619498L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        if (roleList != null && roleList.size() > 0) {
            for (String roleName : roleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                authorities.add(authority);
            }
        }
        return authorities;

    }

    @Override
    public String getUsername() {
        return this.userName;

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return true;

    }

    @Override
    public String getPassword() {
        return this.password;

    }

}
