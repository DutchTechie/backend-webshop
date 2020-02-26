package resources;

import api.Saying;
import api.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final String userEmail;
    private final String userPassword;

    public UserResource(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpHeaders httpHeaders, User source) {
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println(source.getUserEmail());
        return Response.ok(source).build();
    }
}
