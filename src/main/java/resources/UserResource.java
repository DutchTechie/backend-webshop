package resources;

import api.Product;
import api.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/users")
public class UserResource {
    private final AtomicLong counter;
    private final ArrayList<User> users;

    public UserResource() {
        this.users = new ArrayList<User>();
        this.users.add(new User(0, "rkock127@gmail.com", "secret", true));
        this.counter = new AtomicLong();
    }

//    @POST
//    @Path("/signUp/{email}/{password}")
//    public User signUp(@PathParam("email") String email, @PathParam("password") String password) {
//        for (int i = 0; i < this.users.size(); i++) {
//            if (users.get(i).getEmail() == email) {
//                return null;
//            }
//        }
//        User newUser = new User(this.counter.incrementAndGet(), email, password,false);
//        this.users[this.users.length] = newUser;
//        return newUser;
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/signup/{email}/{password}")
    public Response signUp(User user, @PathParam("email") String email, @PathParam("password") String password) {
        for (int i = 0; i < this.users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return null;
            }
        }
        // user = new User(this.counter.incrementAndGet(), email, password,false);
        User newUser = new User(this.counter.incrementAndGet(), email, password,false);
        this.users.add(newUser);
        for (int i = 0; i < this.users.size(); i++) {
            System.out.println(users.get(i).getEmail());
        }
        return Response.ok(newUser).build();
    }

    @POST
    @Path("/login/{email}/{password}")
    public Response login(User user, @PathParam("email") String email, @PathParam("password") String password) {
        for (int i = 0; i < this.users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                if (users.get(i).getPassword().equals(password)) {
                    return Response.ok(users.get(i)).build();
                }
            }
        }
        return null;
    }
}
