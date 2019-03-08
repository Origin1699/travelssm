package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@Transactional

public class UserServiceImpl implements UserService {

    @Resource
    private UserDao dao;

    @Resource
    private BCryptPasswordEncoder encoder;

    public List<SysUser> findAll() {
        return dao.findAll();
    }

    public void save(SysUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        dao.save(user);
    }

    public SysUser findById(String id) {
        return dao.findById(id);
    }

    public List<SysRole> findRolesByID(String id) {
        return dao.findRolesByID(id);
    }

    public void addRoleToUser(String[] ids,String userId) {
        for (String roleId : ids) {
            dao.addRoleToUser(userId,roleId);
        }
    }

    /**
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUser sysUser = dao.findByName(s);
        //System.out.println("==================================");
        if (sysUser==null){
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<SysRole> roles = sysUser.getRoles();
        for (SysRole role : roles) {
            //System.out.println(role);
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        UserDetails user = new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
        return user;
    }
}
