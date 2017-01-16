package com.geoshare.repositories;

import com.geoshare.entities.Note;
import com.geoshare.entities.User;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Singleton
@Service
public class UserRepository {
    private EntityManagerFactory emfactory;

    public UserRepository()
    {
        emfactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
    }

    public User updateAndRetrieveUser(User temporaryUser)
    {
        EntityManager entityManager = emfactory.createEntityManager();

        User user = null;

        try
        {
            user = (User)entityManager.createQuery("SELECT u FROM User u where u.google_instance_id = :googleInstanceId")
                    .setParameter("googleInstanceId", temporaryUser.getGoogle_instance_id()).getSingleResult();

            entityManager.getTransaction().begin();
            if(!temporaryUser.getEmail_address().isEmpty()) {
                user.setEmail_address(temporaryUser.getEmail_address());
            }
            if(!temporaryUser.getNickname().isEmpty()) {
                user.setNickname(temporaryUser.getNickname());
            }
            entityManager.getTransaction().commit();

        }
        catch (javax.persistence.NoResultException e)
        {
            user = temporaryUser;
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }

        entityManager.close();

        return user;
    }
}
