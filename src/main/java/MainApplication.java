import api.User;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import resources.ProductResource;
import resources.UserResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class MainApplication extends Application<BackendWebshopConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public void run(BackendWebshopConfiguration backendWebshopConfiguration, Environment environment) throws Exception {
        this.enableCorsHeaders(environment);
        final ProductResource productResource = new ProductResource();
        final UserResource userResource = new UserResource();
        environment.jersey().register(productResource);
        environment.jersey().register(userResource);
    }

    private void enableCorsHeaders(Environment environment) {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
