package resources;

import api.Saying;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import jdk.jfr.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String description;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String description, String defaultName) {
        this.template = template;
        this.description = description;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying[] sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        final String desc = String.format(description, name.orElse("description"));

        final Saying sayings[] = {
                new Saying(counter.incrementAndGet(), value, desc),
                new Saying(counter.incrementAndGet(), "dummy content", "Dummy desc"),
                new Saying(counter.incrementAndGet(), "dummy content 2", "Dummy desc 2"),
                new Saying(counter.incrementAndGet(), "dummy content 3", "Dummy desc 3"),
                new Saying(counter.incrementAndGet(), "dummy content 4", "Dummy desc 4"),
        };

        return sayings;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSomething(@Context HttpHeaders httpHeaders, Saying source) {
        return Response.ok(source).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAll(@Context HttpHeaders httpHeaders, Saying source) {
        System.out.println("Delete all!");
        source = new Saying(1, "name", "desc");
        return Response.ok(source).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id") String id, @Context HttpHeaders httpHeaders, Saying source) {
        System.out.println("Delete one with id of: " + id);
        source = new Saying(1, "name", "desc");
        return Response.ok(source).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(@Context HttpHeaders httpHeaders, Saying source) {
        System.out.println(httpHeaders.getRequestHeaders().toString());
        System.out.println(source.getName());
        return Response.ok(source).build();
    }
}