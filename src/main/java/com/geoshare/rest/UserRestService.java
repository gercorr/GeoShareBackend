package com.geoshare.rest;

import com.geoshare.entities.Note;
import com.geoshare.entities.User;
import com.geoshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Component
public class UserRestService {

    @Autowired
    private UserRepository userRepository;

    @Path("retrieveUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User retrieveUser(User user)
    {
        return userRepository.retrieveUser(user);
    }


    @Path("createUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(User user)
    {
        return userRepository.createUser(user);
    }

}
