package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao dao;

    public List<Order> findAll() {
        return dao.findeAll();
    }

    public List<Order> pageFind(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);


        return dao.findeAll();
    }
}
