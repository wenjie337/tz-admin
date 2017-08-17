package com.taozhi.mapper;

import java.util.List;

import com.taozhi.dto.SysMenu;
import com.taozhi.dto.SysRole;
import com.taozhi.dto.SysUser;

public interface UserMapper {

    public List<String> getUserRoles(String loginName);

    public List<String> findPermissions(String loginName);

    public List<String> findPermissionRoles(String loginName);

    public SysUser findByUsername(String loginName);

    public List<SysMenu> findUserSysMenu(String loginName);

    public List<SysRole> findAllRole();
}
