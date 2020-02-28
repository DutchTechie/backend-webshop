import api.Cart;
import api.Product;
import api.User;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.checkerframework.checker.units.qual.C;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import resources.CartResource;
import resources.ProductResource;
import resources.UserResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.EnumSet;

public class MainApplication extends Application<BackendWebshopConfiguration> {
    private ArrayList<Product> products;

    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public void run(BackendWebshopConfiguration backendWebshopConfiguration, Environment environment) throws Exception {
        this.enableCorsHeaders(environment);
        this.products = new ArrayList<Product>();
        this.products.add(
                new Product(
                        1,
                        "Nike React Infinity Run Flyknit",
                        "Colour Shown: Plum Fog/Metallic Gold/Platinum Tint/Black\n" +
                                "Style: CD4372-500",
                        "https://c.static-nike.com/a/images/t_PDP_1728_v1/f_auto,b_rgb:f5f5f5/bf7923ee-b28f-4035-9e8d-195ff8ea4703/react-infinity-run-flyknit-running-shoe-CtLQ4J.jpg",
                        100.50,
                        2
                ));

        this.products.add(
                new Product(
                        2,
                        "Nike Air Force 1 High By You",
                        "The Nike Air Force 1 High By You became an instant icon after its debut in 1982. Now you can make this classic your own with a colour palette inspired by the '80s and '90s and premium materials including smooth and rippled leather and a new, matching sidewall selection. It's time to create a look that speaks to you.",
                        "https://c.static-nike.com/a/images/t_PDP_1728_v1/f_auto,b_rgb:f5f5f5/cbce41d8-5a67-4611-a5bd-7296d82e8676/custom-nike-air-force-1-high-by-you.jpg",
                        135.00,
                        7
                ));

        this.products.add(
                new Product(
                        3,
                        "Nike Air Force 1 Low By You",
                        "The Nike Air Force 1 Low By You became an instant icon after its debut in 1982. Now you can make this classic your own with a colour palette inspired by the '80s and '90s and premium materials including smooth and rippled leather and a new, matching sidewall selection. It's time to create a look that speaks to you.",
                        "https://c.static-nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/9f817e00-7699-4886-8017-99164ee45392/custom-nike-air-force-1-by-you.jpg",
                        130.00,
                        7
                ));
        final ProductResource productResource = new ProductResource(products);
        final UserResource userResource = new UserResource();
        final CartResource cartResource = new CartResource(products);
        environment.jersey().register(productResource);
        environment.jersey().register(cartResource);
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
