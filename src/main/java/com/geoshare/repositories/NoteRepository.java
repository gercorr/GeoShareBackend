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
	
	private EntityManager entityManager;

		public NoteRepository()
		{

		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		      entityManager =  emfactory.createEntityManager();
		}
	
	  public List<Note> getAllNotes() {		  

	      Query query = entityManager.createQuery( "Select e from Note e ");
	      
	      List<Note> list=(List<Note>)query.getResultList( );
   	     
		  return list;
	  }
}
