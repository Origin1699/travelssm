package com.itheima.dao;

import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("select * from sys_role")
    public List<SysRole> findAll();

    @Insert("insert into sys_role (roleName, roleDesc)" +
            "values (#{roleName}, #{roleDesc})")
    public void save(SysRole user);


    @Select("select * from sys_role r, sys_user_role ur " +
            "where r.id = ur.roleid and ur.userid=#{uid}")
    public List<SysRole> findByUid();
}
