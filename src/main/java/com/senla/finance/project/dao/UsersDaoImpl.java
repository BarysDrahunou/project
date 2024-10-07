package com.senla.finance.project.dao;

import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;

@Component
@Transactional
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(User user) {
        entityManager.persist(user);
    }

    @Override
    public void merge(User user) {
        entityManager.merge(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        List users = entityManager.createQuery(FIND_USER_BY_EMAIL_QUERY)
                .setParameter(PARAMETER_NUMBER, email).getResultList();
        return users.stream().findFirst();
    }

    public List<User> findAll() {
        return entityManager.createQuery(FIND_ALL_USERS_QUERY).getResultList();
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }
}