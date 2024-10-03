package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Transactional
public class CompaniesDaoImpl implements CompaniesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void mergeAll(List<Company> companies) {
        companies.forEach(entityManager::merge);
    }

    @Override
    public List<String> getAllCompaniesSymbols() {
        return entityManager.createQuery("SELECT c.symbol FROM Company c").getResultList();
    }
}
