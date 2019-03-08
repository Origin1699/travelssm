package com.itheima.service;

import com.itheima.domain.SysRole;

import java.util.List;

public interface RoleService {
    public List<SysRole> findAll();
    public void save(SysRole user);
}
