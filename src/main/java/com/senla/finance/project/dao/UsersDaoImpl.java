package com.senla.finance.project.dao;

import com.senla.finance.project.exceptions.UserAlreadyExistsException;
import com.senla.finance.project.exceptions.UserNotFoundException;
import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static java.lang.String.format;

@Component
@Transactional
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(User user) {
        String email = user.getEmail();
        if (getUserByEmail(email).isEmpty()) {
            entityManager.persist(user);
        } else {
            throw new UserAlreadyExistsException(format(USER_ALREADY_EXISTS_EXCEPTION, email));
        }
    }

    @Override
    public void merge(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserByEmail(String email) {
        if (!getUserByEmail(email).isEmpty()) {
            return (User) getUserByEmail(email).getFirst();
        }

        throw new UserNotFoundException(format(USER_NOT_FOUND_EXCEPTION, email));
    }

    public List<User> findAll() {
        return entityManager.createQuery(FIND_ALL_USERS_QUERY).getResultList();
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return !getUserByEmail(email).isEmpty();
    }

    @Override
    public void deleteUser(String email) {
        List userList = getUserByEmail(email);
        if (!userList.isEmpty()) {
            entityManager.remove(userList.getFirst());
        }
    }

    private List getUserByEmail(String email) {
        return entityManager.createQuery(FIND_USER_BY_EMAIL_QUERY)
                .setParameter(PARAMETER_NUMBER, email).getResultList();
    }
}