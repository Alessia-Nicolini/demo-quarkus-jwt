package it.itsincom.webdevd.web;

import it.itsincom.webdevd.service.UserService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/protected")
@DenyAll
public class ProtectedResource {

    private final UserService userService;
    private final JsonWebToken jwt;

    public ProtectedResource(UserService userService, JsonWebToken jwt) {
        this.userService = userService;
        this.jwt = jwt;
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



}
