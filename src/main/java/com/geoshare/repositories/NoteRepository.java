package com.geoshare.repositories;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.geoshare.entities.User;
import org.springframework.stereotype.Service;

import com.geoshare.entities.Note;

@Singleton
@Service
public class NoteRepository implements INoteRepository
{

	private EntityManagerFactory emfactory;

	public NoteRepository()
	{
		emfactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
	}

	public List<Note> getAllNotes()
	{
		EntityManager entityManager = emfactory.createEntityManager();
		Query query = entityManager.createQuery("Select e from Note e ");
		List<Note> list = (List<Note>) query.getResultList();
		entityManager.close();
		return list;
	}

	public Note getOneTestNote()
	{
		EntityManager entityManager = emfactory.createEntityManager();
		Query query = entityManager.createQuery("Select e from Note e").setMaxResults(1);
		Note singleNote = (Note) query.getSingleResult();
		entityManager.close();
		return singleNote;
	}

	public List<Note> getAllNotesForUser(User user)
	{
		EntityManager entityManager = emfactory.createEntityManager();
		Query query = entityManager
				.createQuery("Select e from Note e where e.user=:user ")
				.setParameter("user", user);

		List<Note> list = (List<Note>) query.getResultList();
		entityManager.close();
		return list;
	}

	public List<Note> getAllNotesWithinDistance(double latitude, double longitude, double distance)
	{
		double xMinus02 = latitude - distance;
		double xPlus02 = latitude + distance;

		double yminus02 = longitude - distance;
		double yPlus02 = longitude + distance;

		EntityManager entityManager = emfactory.createEntityManager();
		Query query = entityManager
				.createQuery("Select e from Note e " + "where latitude between :xMinus02 and :xPlus02 "
						+ "and longitude between :yminus02 and :yPlus02")
				.setParameter("xMinus02", xMinus02).setParameter("xPlus02", xPlus02).setParameter("yminus02", yminus02)
				.setParameter("yPlus02", yPlus02);

		List<Note> list = (List<Note>) query.getResultList();
		entityManager.close();
		return list;
	}

	public void SaveNote(Note note)
	{
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(note);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
