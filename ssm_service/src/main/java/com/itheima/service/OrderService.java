package com.itheima.service;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public List<Order> pageFind(Integer pageNum, Integer pageSize);
}
