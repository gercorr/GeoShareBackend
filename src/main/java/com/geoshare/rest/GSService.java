package com.geoshare.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geoshare.entities.Note;
import com.geoshare.repositories.NoteRepository;
import com.geoshare.xml_loader.XmlLoader;

@Path("/")
@Component
public class GSService
{

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private XmlLoader xmlLoader;

	// need to change this from get
	// eg http://localhost:8080/rest/addNote/text=ger4&lat=53.3&long=-6.3
	// @Path("addNote/text={text}&lat={lat}&long={long}")
	@Path("addNote")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Note addNote(@QueryParam("text") String text, @QueryParam("lat") double latitude,
			@QueryParam("long") double longitude)
	{
		Note note = new Note();
		note.setText(text);
		note.setLatitude(latitude);
		note.setLongitude(longitude);
		noteRepository.SaveNote(note);

		return note;

	}

	@Path("/getAllNotes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getAllNotes()
	{
		return noteRepository.getAllNotes();
	}

	// http://localhost:8080/rest/getAllNotesWithinDistance?lat=53.32121&long=-6.33114&distance=0.02
	@Path("/getAllNotesWithinDistance")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getAllNotesWithinDistance(@QueryParam("lat") double latitude,
			@QueryParam("long") double longitude, @QueryParam("distance") double distance)
	{
		return noteRepository.getAllNotesWithinDistance(latitude, longitude, distance);
	}

	@Path("/importXml")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> importXml(@QueryParam("password") String text)
	{
		if (text.equals("tempbiglongpasswordforimportxml"))// should put this in
															// config. Doesnt
															// matter files are
															// deleted after
															// import
			xmlLoader.ImportFile();

		return getAllNotes();
	}

}