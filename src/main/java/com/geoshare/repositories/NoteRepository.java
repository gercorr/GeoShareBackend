package com.geoshare.repositories;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.geoshare.entities.User;
import com.geoshare.pojos.NotesRequest;
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

	public List<Note> getAllNotesWithinDistance(NotesRequest noteRequest)
	{
		double xMinus02 = noteRequest.getLatitude() - noteRequest.getDistance();
		double xPlus02 = noteRequest.getLatitude() + noteRequest.getDistance();

		double yminus02 = noteRequest.getLongitude() - noteRequest.getDistance();
		double yPlus02 = noteRequest.getLongitude() + noteRequest.getDistance();

		String nickname = noteRequest.getNickname();

		EntityManager entityManager = emfactory.createEntityManager();
		Query query = entityManager
				.createQuery("Select note from Note note " +
						"inner join note.user user " +
						"where (:nickname is null or user.nickname=:nickname) " +
						"and note.latitude between :xMinus02 and :xPlus02 "
						+ "and note.longitude between :yminus02 and :yPlus02 ")
				.setParameter("xMinus02", xMinus02).setParameter("xPlus02", xPlus02).setParameter("yminus02", yminus02)
				.setParameter("nickname", nickname)
				.setParameter("yPlus02", yPlus02);

		List<Note> list = (List<Note>) query.getResultList();
		entityManager.close();
		return list;
	}

	public void SaveNote(Note note)
	{
		note.setCreatedDate(new Date());

		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(note);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
