package com.geoshare.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.geoshare.entities.User;
import com.geoshare.pojos.NotesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geoshare.entities.Note;
import com.geoshare.repositories.NoteRepository;
import com.geoshare.xml_loader.XmlLoader;

@Path("/")
@Component
public class NoteRestService
{

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private XmlLoader xmlLoader;

	@Path("addNote")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Note addNote(Note note)
	{
		noteRepository.SaveNote(note);

		return note;

	}

	//Used for browser testing only (to check everything is working)
	@Path("/getAllNotes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getAllNotes()
	{
		return noteRepository.getAllNotes();
	}

	@Path("getAllNotesWithinDistance")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Note> getAllNotesWithinDistance(NotesRequest noteRequest)
	{
		return noteRepository.getAllNotesWithinDistance(noteRequest);
	}

	@Path("getAllNotesForUser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Note> getAllNotesForUser(User user)
	{
		return noteRepository.getAllNotesForUser(user);
	}

	@Path("/importXml")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> importXml(@QueryParam("password") String text)
	{
		if (text.equals("tempbiglongpasswordforimportxml"))// should put this in
															// config. Doesn't
															// matter files are
															// deleted after
															// import
			xmlLoader.ImportFile();

		return getAllNotes();
	}

}