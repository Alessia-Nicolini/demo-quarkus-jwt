package it.itsincom.webdevd.web;

import it.itsincom.webdevd.service.UserService;
import it.itsincom.webdevd.web.model.user.CreateUserRequest;
import it.itsincom.webdevd.web.model.user.ModifyUserRequest;
import it.itsincom.webdevd.web.model.user.UserResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;
import java.util.Set;

@Path("/user")
public class UserResource {

    private final UserService userService;
    private final JsonWebToken jwt;

    public UserResource(UserService userService, JsonWebToken jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response register(CreateUserRequest request) {
        UserResponse user = userService.createUser(request);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> findAll() {
        return userService.findAll();
    }


    @PUT
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(ModifyUserRequest request, @PathParam("id") Long id) {
        Set<String> currentUserRoles = jwt.getGroups();
        if (currentUserRoles.contains("ADMIN") || Integer.parseInt(jwt.getSubject()) == id) {
            UserResponse modifiedUser = userService.modifyUser(request, id);
            return Response.ok(modifiedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
