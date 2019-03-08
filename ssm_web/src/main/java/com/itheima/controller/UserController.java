package com.itheima.controller;


import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService service;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<SysUser> list = service.findAll();
        model.addAttribute("list", list);
        return "user-list2";


    }

    @RequestMapping("/save")
    public String save(Model model, SysUser user) {
        service.save(user);
        return "redirect:findAll";
    }

    @RequestMapping("/findDetail")
    public String findDetail(String id, Model model) {
        SysUser sysUser = service.findById(id);
        model.addAttribute("user", sysUser);
        return "user-show";
    }

    @RequestMapping("/findRolesByID")
    public String findRolesByID(String id, Model model) {
        List<SysRole> list = service.findRolesByID(id);
        model.addAttribute("roleList", list);
        model.addAttribute("id", id);
        return "user-role-add";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String[] ids, String userId, Model model) {
        //System.out.println(ids);
        service.addRoleToUser(ids, userId);
        return "redirect:findAll";
    }

    @RequestMapping("/showName")
    public void showName(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String user = request.getRemoteUser();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        response.getWriter().print(user);
    }
}



