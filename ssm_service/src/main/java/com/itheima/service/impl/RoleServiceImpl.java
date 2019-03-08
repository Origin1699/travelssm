package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.SysRole;
import com.itheima.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
@Transactional
@RolesAllowed({"ROLE_ADMIN","ROLE_ROLE"})
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao dao;

    public List<SysRole> findAll() {
        return dao.findAll();
    }

    public void save(SysRole user) {
            dao.save(user);
    }
}
