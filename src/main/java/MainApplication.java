import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import resources.HelloWorldResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class MainApplication extends Application<BackendWebshopConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<BackendWebshopConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(BackendWebshopConfiguration backendWebshopConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
            backendWebshopConfiguration.getTemplate(),
            backendWebshopConfiguration.getDescription(),
            backendWebshopConfiguration.getDefaultName()
        );

        final HelloWorldResource resources[] = {
          resource,
          resource
        };

        environment.jersey().register(resource);

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
