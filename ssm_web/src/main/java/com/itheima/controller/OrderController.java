package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService service;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<Order> list = service.findAll();
        model.addAttribute("list", list);
        return "order-list";
    }

    @RequestMapping("/pageFind")
    public String pageFind(Model model,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "3") Integer pageSize) {
        List<Order> a = service.pageFind(pageNum, pageSize);
        PageInfo<Order> info = new PageInfo<Order>(a);
        model.addAttribute("info", info);
        return "order-list";
    }

}
