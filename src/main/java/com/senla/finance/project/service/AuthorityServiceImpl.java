package com.senla.finance.project.service;

import com.senla.finance.project.dao.AuthorityDao;
import com.senla.finance.project.model.roles.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public void deleteAllAuthorities() {
        authorityDao.deleteAllAuthorities();
    }

    @Override
    public void addAllAuthorities(List<Authority> authorities) {
        authorityDao.addAllAuthorities(authorities);
    }
}
