package com.itheima.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public void save(Product product);

    public Product updateFindById(String id);

    public void update(Product product);

    void deleteAll(String[] ids);

    public PageBean pageFind(Integer pageNum, Integer pageSize);

    List<Product> pageHelper(Integer pageNum, Integer pageSize);

}
