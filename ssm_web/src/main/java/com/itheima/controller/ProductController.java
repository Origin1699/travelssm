package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertiesEditor() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(text);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setValue(date);
            }
        });
    }


    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<Product> list = service.findAll();
        System.out.println(list);
        model.addAttribute("list", list);
        return "product-list";
    }

    @RequestMapping("/save")
    public String save(Product product) {
        System.out.println(product);
        service.save(product);
        return "redirect:findAll";
    }

    @RequestMapping("/updateFindById")
    public String updateFindById(String id,Model model){
       Product product = service.updateFindById(id);
       model.addAttribute("product",product);
       return "product-update";
    }
    @RequestMapping("/update")
    public String update(Product product){
        service.update(product);
        return "redirect:findAll";
    }
    @RequestMapping("/delAll")
    public String deleteAll(String[] ids){
        service.deleteAll(ids);
        return "redirect:findAll";
    }
    @RequestMapping("/pageFind")
    public String pageFind(Model model,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "3") Integer pageSize){
        PageBean pageBean = service.pageFind(pageNum,pageSize);
        System.out.println(pageBean);
        model.addAttribute("page",pageBean);
        return "product-list";
    }

    @RequestMapping("/pageHelper")
    public String pageHelper(Model model,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "3") Integer pageSize){
        List<Product> list = service.pageHelper(pageNum,pageSize);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        System.out.println(pageInfo);
        model.addAttribute("page",pageInfo);
        return "product-list2";
    }
}
