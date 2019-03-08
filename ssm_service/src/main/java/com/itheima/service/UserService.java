package com.itheima.service;

import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<SysUser> findAll();
    public void save(SysUser user);

    public SysUser findById(String id);

    public List<SysRole> findRolesByID(String id);

    public void addRoleToUser(String[] ids,String userId);
}
