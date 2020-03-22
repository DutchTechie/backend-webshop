package resources;

import api.Cart;
import api.Product;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    private ArrayList<Product> products;
    private final AtomicLong counter;

    public ProductResource(ArrayList<Product> products) {
        this.counter = new AtomicLong();
        this.products = products;
    }

    private void fetchAllProductsOnce() {
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
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewProduct(Product product) {
        product.id = this.products.size() + 1;
        this.products.add(product);
        return Response.ok(product).build();
    }

    @GET
    public Object [] fetchAllProducts() {
        return this.products.toArray();
    }

    @GET
    @Path("{id}")
    public Product fetchProduct(@PathParam("id") long id) {
        for (int i = 0; i < this.products.size(); i++) {
            if (products.get(i).getId() == id) {
                return products.get(i);
            }
        }
        return null;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
        for (int i = 0; i < this.products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product); //remove(i);
            }
        }
        return Response.ok(product).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{productId}")
    public Product deleteProduct(@PathParam("productId") long productId) {
        System.out.println("Delete Product resource!");
        for (int i = 0; i < this.products.size(); i++) {
            if (products.get(i).getId() == productId) {
                return products.remove(i);
            }
        }
        return null;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteAllProducts() {
        this.products.clear();
        return this.products.isEmpty();
    }
}
