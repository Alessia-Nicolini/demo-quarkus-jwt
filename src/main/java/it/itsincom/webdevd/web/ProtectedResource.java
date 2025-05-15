package it.itsincom.webdevd.web;

import it.itsincom.webdevd.service.UserService;
import it.itsincom.webdevd.web.model.user.CreateUserRequest;
import it.itsincom.webdevd.web.model.user.ModifyUserRequest;
import it.itsincom.webdevd.web.model.user.UserResponse;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/protected")
@DenyAll
public class ProtectedResource {

    private final UserService userService;

    public ProtectedResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/admin")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_PLAIN)
    public String onlyForAdmins() {
        return "Only for admins!";
    }

    @GET
    @Path("/user")
    @RolesAllowed({"ADMIN", "USER"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String onlyForUsers() {

        return "Only for users!";
    }

    @GET
    @Path("/all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String forEveryone() {
        return "For everyone!";
    }


    @PUT
    @Path("/{id}")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(ModifyUserRequest request, @PathParam("id") int id ) {
        boolean modified = userService.modifyUser(request, id);
        if(modified) {
            return Response.ok(request).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/newUser")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateUserRequest request) {
        UserResponse user = userService.createUser(request);
        if(user != null) {
            return Response.ok(user).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



}
