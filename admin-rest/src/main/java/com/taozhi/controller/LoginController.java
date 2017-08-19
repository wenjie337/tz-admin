package com.taozhi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.taozhi.common.utils.TextUtil;
import com.taozhi.dto.SysMenu;
import com.taozhi.svc.SysService;

@Controller
public class LoginController {

    //private final static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SysService sysService;

    @RequestMapping("/signin")
    public String signin(ModelMap map, HttpServletRequest request) {
        return "/page/signin";
    }

    @RequestMapping("/")
    public String index(ModelMap map, HttpServletRequest request) {
        return "/page/signin";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        return "redirect:/signin.htm";
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = userDetails.getUsername();
        List umenus = (List) request.getSession().getAttribute("usermenus");
        if (TextUtil.isValid(username) && (umenus == null || umenus.isEmpty())) {
            List<SysMenu> menus = sysMenu(username);
            request.getSession().setAttribute("usermenus", menus);
        }

        return "/page/index";
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "/page/_welcome";
    }

    private List<SysMenu> sysMenu(String username) {
        List<SysMenu> menus = sysService.findUserSysMenu(username);

        List<SysMenu> list = new ArrayList<SysMenu>();
        for (SysMenu menu : menus) {
            list.add((SysMenu) menu.clone());
        }

        List<SysMenu> ms = new ArrayList<SysMenu>();
        if (!(list instanceof Page)) {
            for (SysMenu menu : list) {
                if (menu.getParentId() == 0) {
                    if (menu.getMenuType() == null || menu.getMenuType() == 0) {
                        continue;
                    }
                    ms.add(menu);
                    subs(menu, list);
                }
            }
        }

        return ms;
    }

    private void subs(SysMenu menu, List<SysMenu> list) {
        for (SysMenu sub : list) {
            if (sub.getParentId() == menu.getId()) {
                if (menu.getSubs() == null) {
                    menu.setSubs(new ArrayList<SysMenu>());
                }
                if (sub.getMenuType() == null || sub.getMenuType() == 0) {
                    continue;
                }
                menu.getSubs().add(sub);
                subs(sub, list);
            }

        }
    }

    @RequestMapping("/unauthorized")
    public ModelAndView unauthorized(ModelAndView mav) {
        mav.setViewName("/unauthorized");
        return mav;
    }

}
