package resources;

import api.Saying;
import com.codahale.metrics.annotation.Timed;
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
                new Saying(counter.incrementAndGet(), "dummy content", "Dummy desc")
        };

        return sayings;
    }

//    @POST
//    public Response add(/*@PathParam("user") OptionalLong userId,
//                        @NotNull @Valid Notification notification*/) {
//        // final long id = store.add(userId.get(), notification);
//        // return Response.created(UriBuilder.fromResource(NotificationResource.class)
//                // .build(userId.get(), id))
//                // .build();
//        return Response.created(UriBuilder.fromResource(HelloWorldResource.class))
//                .build();
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSomething(@Context HttpHeaders httpHeaders, Saying source) {
        // System.out.println(source.description);
        return Response.ok(source).build();

        // return new Saying(1, source.template, "desc");
    }
}