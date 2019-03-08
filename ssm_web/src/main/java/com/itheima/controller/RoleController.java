package com.itheima.controller;

import com.itheima.domain.SysRole;
import com.itheima.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService service;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<SysRole> list = service.findAll();

        model.addAttribute("roleList", list);

        return "role-list";
    }

    @RequestMapping("/save")
    public String save(SysRole role) {
        service.save(role);
        return "redirect:findAll";
    }
}
