package com.geoshare.repositories;

import java.util.List;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.annotation.ManagedBean;
import javax.persistence.*;
import javax.inject.Singleton;

import org.springframework.stereotype.Service;

import com.geoshare.entities.Note;
@Singleton
@Service
public class NoteRepository implements INoteRepository {
	
	private EntityManagerFactory emfactory;

	public NoteRepository()
	{
		emfactory = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
	}
	
	public List<Note> getAllNotes() {		  		 
		EntityManager entityManager =  emfactory.createEntityManager();
		Query query = entityManager.createQuery( "Select e from Note e ");			      
		List<Note> list=(List<Note>)query.getResultList( );		
		entityManager.close();
		return list;  
	}
	  
  public void SaveNote(Note note)
  {
		EntityManager entityManager =  emfactory.createEntityManager();
		entityManager.getTransaction( ).begin( );			  
		entityManager.persist( note );
		entityManager.getTransaction( ).commit( );	
	  	entityManager.close();		
  }
	  
}
