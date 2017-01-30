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

    public User retrieveUser(User temporaryUser)
    {
        EntityManager entityManager = emfactory.createEntityManager();

        User user;

        try
        {
            //if user for id exists then update it
            user = (User)entityManager.createQuery("SELECT u FROM User u where u.google_instance_id = :googleInstanceId")
                    .setParameter("googleInstanceId", temporaryUser.getGoogle_instance_id()).getSingleResult();

            entityManager.getTransaction().begin();
            if(temporaryUser.getEmail_address() != null && !temporaryUser.getEmail_address().isEmpty()) {
                user.setEmail_address(temporaryUser.getEmail_address());
            }
            if(temporaryUser.getNickname() != null && !temporaryUser.getNickname().isEmpty()) {
                user.setNickname(temporaryUser.getNickname());
            }
            entityManager.getTransaction().commit();

        }
        catch (javax.persistence.NoResultException e)
        {
            user = null;
        }

        entityManager.close();

        return user;
    }

    public User createUser(User temporaryUser)
    {
        EntityManager entityManager = emfactory.createEntityManager();

        User user;

        try {
            user = (User)entityManager.createQuery("SELECT u FROM User u where u.nickname = :nickname")
                    .setParameter("nickname", temporaryUser.getNickname()).getSingleResult();
            user = null; //username is in use. Return null
        }
        catch (javax.persistence.NonUniqueResultException e)
        {
            user = null; //multiple results. Return null
        }
        catch (javax.persistence.NoResultException e2)
        {
            //user doesnt exist for id of nickname so create it
            user = temporaryUser;
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }

        entityManager.close();

        return user;
    }
}
