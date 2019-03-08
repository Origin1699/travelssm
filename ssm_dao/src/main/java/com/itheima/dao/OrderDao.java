package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {

    @Select("select * from orders")
    @Results(
            @Result(property = "product" ,column = "productId",javaType = Product.class,
            one =@One(select = "com.itheima.dao.ProductDao.updateFindById")
            )
    )
    public List<Order> findeAll();

}
