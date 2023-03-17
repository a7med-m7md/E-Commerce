package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.entities.User;
import com.laphup.persistence.repository.BaseRepo;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepo extends BaseRepo<User, Integer> {

    private EntityManager entityManager;

    public UserRepo(HttpServletRequest request) {
        super(request);
        if (request == null)
            System.out.println("UserDao");
        entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    public Optional<User> getByEmail(String email) {
        String queryString = "from User u where u.eMail = :email";
        Query q = entityManager.createQuery(queryString);
        q.setParameter("email", email);
        List<User> users = q.getResultList();
        if (users.isEmpty())
            return Optional.empty();
        else
            return Optional.of(users.get(0));
    }

    public List<User> getAllUsers(){
        List<User> users = entityManager.createNamedQuery("getAllUsers").getResultList();
        return users;
    }

    public User getUserById(UUID id){
        //todo Here an exception may occur for invalid user id profile
        User user = (User)entityManager.createNamedQuery("getUserById").setParameter("id", id).getSingleResult();
        return user;
    }
}
