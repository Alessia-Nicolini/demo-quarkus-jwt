package it.itsincom.webdevd.web;

import it.itsincom.webdevd.service.UserService;
import it.itsincom.webdevd.web.model.user.CreateUserRequest;
import it.itsincom.webdevd.web.model.user.ModifyUserRequest;
import it.itsincom.webdevd.web.model.user.UserResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse register(CreateUserRequest request) {
        return userService.createUser(request);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> findAll() {
        return userService.findAll();
    }



}
