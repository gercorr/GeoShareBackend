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
	private EntityManagerFactory emfactory;

	public NoteRepository()
	{
		emfactory = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
	    entityManager =  emfactory.createEntityManager();
	}
	
	  public List<Note> getAllNotes() {		  		  
	  
		  int count = 0;
		  int maxTries = 2;
		  while(true) {
		      try {
			      Query query = entityManager.createQuery( "Select e from Note e ");			      
			      List<Note> list=(List<Note>)query.getResultList( );		   	     
			      return list;
	      } catch (Exception e) {
	          // handle exception
	          if (++count == maxTries) throw e;
	
	          	emfactory = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
			    entityManager =  emfactory.createEntityManager();
		      }
		  }  
			  
	
	  }
	  
	  public void SaveNote(Note note)
	  {
		  int count = 0;
		  int maxTries = 2;
		  while(true) {
		      try {
		    	  entityManager.getTransaction( ).begin( );			  
		    	  entityManager.persist( note );
		    	  entityManager.getTransaction( ).commit( );
		    	  return;
		      } catch (Exception e) {
		          // handle exception
		          if (++count == maxTries) throw e;
		
		          	emfactory = Persistence.createEntityManagerFactory( "Hibernate_JPA" );
				    entityManager =  emfactory.createEntityManager();
			      }
			  }  
			
	  }
	  
}
