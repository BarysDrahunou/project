package com.senla.finance.project.service;

import com.senla.finance.project.model.roles.Authority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorityService {

    void deleteAllAuthorities();

    void addAllAuthorities(List<Authority> authorities);
}
