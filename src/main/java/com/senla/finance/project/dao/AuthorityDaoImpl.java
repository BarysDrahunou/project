package com.senla.finance.project.dao;

import com.senla.finance.project.model.roles.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.senla.finance.project.utils.Constants.DELETE_ALL_AUTHORITIES_QUERY;

@Component
@Transactional
public class AuthorityDaoImpl implements AuthorityDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void deleteAllAuthorities() {
        entityManager.createQuery(DELETE_ALL_AUTHORITIES_QUERY).executeUpdate();
    }

    @Override
    public void addAllAuthorities(List<Authority> authorities) {
        authorities.forEach(entityManager::persist);
    }
}
