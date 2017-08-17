package com.taozhi.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhi.dto.SysMenu;
import com.taozhi.dto.SysUser;
import com.taozhi.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<String> getUserRoles(String loginName) {
        return userMapper.getUserRoles(loginName);
    }

    public List<String> findPermissions(String loginName) {
        return userMapper.findPermissions(loginName);
    }

    public List<String> findPermissionRoles(String loginName) {
        return userMapper.findPermissionRoles(loginName);
    }

    public SysUser findByUsername(String loginName) {
        return userMapper.findByUsername(loginName);
    }

    public List<SysMenu> findUserSysMenu(String loginName) {
        return userMapper.findUserSysMenu(loginName);
    }

}
