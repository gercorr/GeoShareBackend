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

    // need to change this from get
    // eg http://localhost:8080/rest/updateAndRetrieveUser?nickname=gercorr&google_instance_id=abcdefg&email_address=gercorr@gmail.com
    // @Path("addNote/text={text}&lat={lat}&long={long}")
    @Path("updateAndRetrieveUser")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User updateAndRetrieveUser(@QueryParam("nickname") String nickname, @QueryParam("google_instance_id") String google_instance_id, @QueryParam("email_address") String email_address)
    {
        User user = new User();
        user.setNickname(nickname);
        user.setGoogle_instance_id(google_instance_id);
        user.setEmail_address(email_address);
        user = userRepository.updateAndRetrieveUser(user);

        return user;
    }

    // need to change this from get
    // eg
    // @Path("")
    @Path("updateAndRetrieveUserJson")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateAndRetrieveUserJson(User jsonUser)
    {
        User user = new User();

        return user;
    }


}
