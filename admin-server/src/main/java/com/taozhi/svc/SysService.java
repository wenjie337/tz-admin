package com.taozhi.svc;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taozhi.common.utils.TextUtil;
import com.taozhi.dto.SysHKey;
import com.taozhi.dto.SysMenu;
import com.taozhi.dto.SysRole;
import com.taozhi.dto.SysUser;
import com.taozhi.mapper.SysMapper;

@Service
public class SysService {

    @Autowired
    private SysMapper sysMapper;

    public int addSysUser(SysUser sysuser) {
        if (sysuser == null) {
            return 0;
        }
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String password = encoder.encodePassword(sysuser.getUsername(), sysuser.getPassword());
        sysuser.setPassword(password);
        return sysMapper.addSysUser(sysuser);

    }

    public List<SysUser> findSysUser() {
        PageHelper.startPage(1, 10);
        List<SysUser> list = sysMapper.findSysUser();

        return list;
    }

    public void delSysUser(String ids) {
        if (ids == null) return;
        sysMapper.delSysUser(ids.split("@"));
    }

    public SysUser findSysUserById(int id) {
        return sysMapper.findSysUserById(id);
    }

    public int updSysUser(SysUser sysuser) {
        if (sysuser == null) return 0;

        if (sysuser.getPassword() != null && !"".equals(sysuser.getPassword())) {
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String password = encoder.encodePassword(sysuser.getUsername(), sysuser.getPassword());
            sysuser.setPassword(password);
        }

        return sysMapper.updSysUser(sysuser);
    }

    public List<SysRole> findSysRole() {
        List<SysRole> list = sysMapper.findSysRole();

        return list;
    }

    public List<SysMenu> findSysMenu(int[] arr) {
        if (arr != null && arr.length > 0 && arr[0] > 0) {
            if (arr.length > 2) {
                PageHelper.startPage(arr[1], arr[2]);
            }
        }

        List<SysMenu> list = sysMapper.findSysMenu();

        return list;
    }

    public int addSysMenu(SysMenu sysmenu) {
        return sysMapper.addSysMenu(sysmenu);
    }

    public int addSysRoleMenu(Map map) {
        if (map == null) return 0;
        String roleId = (String) map.get("roleId");
        String menuIds = (String) map.get("menuIds");

        if (!TextUtil.isValidNumber(roleId) || !TextUtil.isValid(menuIds)) {
            return 0;
        }

        sysMapper.delSysRoleMenu(map);

        String[] arr = menuIds.split(",");

        int ret = 0;
        for (String menuId : arr) {
            map.put("menuId", menuId);
            ret = sysMapper.addSysRoleMenu(map);
        }

        return ret;
    }

    public List<SysMenu> findSysRoleMenu(int roleId) {
        return sysMapper.findSysRoleMenu(roleId);
    }

    public List<SysRole> findSysUserRole(int userId) {
        return sysMapper.findSysUserRole(userId);
    }

    public int addSysUserRole(Map map) {
        if (map == null) return 0;
        String userId = (String) map.get("userId");
        String roleIds = (String) map.get("roleIds");

        if (!TextUtil.isValidNumber(userId) || !TextUtil.isValid(roleIds)) {
            return 0;
        }

        sysMapper.delSysUserRole(map);

        String[] arr = roleIds.split(",");

        int ret = 0;
        for (String roleId : arr) {
            map.put("roleId", roleId);
            ret = sysMapper.addSysUserRole(map);
        }

        return ret;
    }

    public SysMenu findSysMenuById(int id) {
        return sysMapper.findSysMenuById(id);
    }

    public int updSysMenu(SysMenu sysmenu) {
        return sysMapper.updSysMenu(sysmenu);
    }

    public List<SysMenu> findUserSysMenu(String username) {
        return sysMapper.findUserSysMenu(username);
    }

    public void delSysMenu(String ids) {
        if (ids == null) return;
        sysMapper.delSysMenu(ids.split("@"));
    }

    public SysUser findSysUserByName(String username) {
        if (StringUtils.isBlank(username)) return null;

        return sysMapper.findSysUserByName(username);
    }

    public List<SysHKey> findSysHKey(Object... arr) {
        if (arr != null && arr.length > 0) {
            PageHelper.startPage((int) arr[0], 10);
        }
        SysHKey syshkey = null;
        if (arr.length > 1) {
            syshkey = (SysHKey) arr[1];
        }
        return sysMapper.findSysHKey(syshkey);
    }

    public int addSysHKey(SysHKey syshkey) {
        return sysMapper.addSysHKey(syshkey);
    }

    public SysHKey findSysHKeyById(int id) {
        return sysMapper.findSysHKeyById(id);
    }

    public int updSysHKey(SysHKey syshkey) {
        return sysMapper.updSysHKey(syshkey);
    }

    public void delSysHKey(String ids) {
        if (ids == null) return;
        sysMapper.delSysHKey(ids.split("@"));
    }

    public static void main(String[] args) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String password = encoder.encodePassword("admin", "admin");
        System.out.println(password);
    }
}
