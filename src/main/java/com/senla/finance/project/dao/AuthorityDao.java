package com.senla.finance.project.dao;

import com.senla.finance.project.model.roles.Authority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorityDao {

    void deleteAllAuthorities();

    void addAllAuthorities(List<Authority> authorities);
}
