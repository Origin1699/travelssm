package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
@Transactional
@RolesAllowed({"ROLE_ADMIN","ROLE_PRODCET"})
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PRODUCT')")
@Secured({"ROLE_ADMIN","ROLE_PRODCET"})
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao dao;

    public List<Product> findAll() {
        return dao.findAll();
    }

    public void save(Product product) {
        dao.save(product);
    }

    public Product updateFindById(String id) {
        return dao.updateFindById(id);
    }

    public void update(Product product) {
        dao.update(product);
    }

    public void deleteAll(String[] ids) {
        for (String id : ids) {
            dao.delById(id);
        }
    }

    public PageBean pageFind(Integer pageNum, Integer pageSize) {

        Integer totalCount = dao.countProduct();
        System.out.println(totalCount+"-------------------------------------");

        PageBean bean = new PageBean(pageNum, pageSize, totalCount);

        List<Product> list = dao.pageFind(bean);

        bean.setList(list);

        return bean;
    }

    public List<Product> pageHelper(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return dao.pageHelper();
    }
}
