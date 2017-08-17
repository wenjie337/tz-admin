package com.taozhi.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.taozhi.dto.SysUser;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO
        SysUser user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        List<String> sysRoles = userService.getUserRoles(username);
        SecurityUser securityUser = new SecurityUser(user, sysRoles);
        //        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return securityUser;

    }

}
