package com.geoshare.rest;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geoshare.entities.Note;
import com.geoshare.repositories.NoteRepository;

 
@Path("/")
@Component
public class GSService {

    @Autowired
    private NoteRepository noteRepository;	

	  //need to change this from get
    //eg http://localhost:8080/rest/addNote/text=ger4&lat=53.3&long=-6.3
	  //@Path("addNote/text={text}&lat={lat}&long={long}")
	  @Path("addNote")
	  @GET
	    @Produces(MediaType.APPLICATION_JSON)
	  public Note addNote(@QueryParam("text") String text, @QueryParam("lat") float latitude, @QueryParam("long") float longitude) {
		  
		  Note note = new Note( ); 
		  note.setText(text);
		  note.setLatitude(latitude);
		  note.setLongitude(longitude);
		  noteRepository.SaveNote(note);	
		  
		   return note;
		  
	  }
	  

	  @Path("/getAllNotes")
	  @GET
	    @Produces(MediaType.APPLICATION_JSON)
	  public List<Note> getAllNotes() {
		  
	      return noteRepository.getAllNotes();	      
	  }
	  
	  
	  
	  
}