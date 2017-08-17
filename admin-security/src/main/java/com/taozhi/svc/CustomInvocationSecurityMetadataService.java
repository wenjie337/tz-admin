package com.taozhi.svc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.taozhi.common.utils.MapperUtils;
import com.taozhi.dto.SysMenu;
import com.taozhi.dto.SysRole;

@Service
public class CustomInvocationSecurityMetadataService implements FilterInvocationSecurityMetadataSource {

    private static Logger LOG = LoggerFactory.getLogger(CustomInvocationSecurityMetadataService.class);

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    @Autowired
    private SysService sysService;

    @PostConstruct
    public void loadResourceDefine() {
        List<SysRole> list = sysService.findSysRole();
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        for (SysRole role : list) {
            ConfigAttribute ca = new SecurityConfig(role.getRoleName());
            List<String> query1 = new ArrayList<String>();
            List<SysMenu> list1 = sysService.findSysRoleMenu(role.getId());
            if (list1 != null && list1.size() > 0) {
                for (SysMenu menu : list1) {
                    query1.add(menu.getMenuUrl());
                }
            }
            if (query1 != null && !query1.isEmpty()) {
                for (String res : query1) {
                    String url = res;
                    /* 
                     * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。 
                     * sparta 
                     */
                    if (resourceMap.containsKey(url)) {

                        Collection<ConfigAttribute> value = resourceMap.get(url);
                        value.add(ca);
                        resourceMap.put(url, value);
                    } else {
                        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                        atts.add(ca);
                        resourceMap.put(url, atts);
                    }

                }
            }
        }
        LOG.info("load:-----" + MapperUtils.toJson(resourceMap));

    }

    // 根据URL，找到相关的权限配置
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。  
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        LOG.info("get:-----" + MapperUtils.toJson(ite));
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return true;

    }

}
