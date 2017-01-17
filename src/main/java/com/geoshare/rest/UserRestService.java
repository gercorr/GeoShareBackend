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

    @Path("updateAndRetrieveUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateAndRetrieveUser(User user)
    {
        return userRepository.updateAndRetrieveUser(user);
    }


}
