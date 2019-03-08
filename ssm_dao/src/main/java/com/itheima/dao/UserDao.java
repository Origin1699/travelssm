package com.itheima.dao;

import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface UserDao {

    @Select("select * from sys_user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findByUid"))
    })
    public List<SysUser> findAll();

    @Insert("insert into sys_user ( username, password, email, phoneNum, status)" +
            "values (#{username}, #{password}, #{email}, #{phoneNum}, #{status})")
    public void save(SysUser user);

    @Select("select * from sys_user where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findByUid")
            )}
    )
    public SysUser findByName(String username);

    @Select("select * from sys_user where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findByUid"))
    })
    SysUser findById(String id);



    @Select("select * from sys_role r where id not in(select roleid from sys_user_role where userid = #{id})")
    public List<SysRole> findRolesByID(String id);

    @Insert("insert into sys_user_role (userid , roleid ) values (#{userId}, #{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
