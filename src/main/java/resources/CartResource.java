package resources;

import api.Cart;
import api.Product;
import api.ShoppingCart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/shoppingCart")
public class CartResource {
    private ArrayList<Product> product;
    private ArrayList<Cart> cart;

    public CartResource(ArrayList<Product> products) {
        this.product = products;
        this.cart = new ArrayList<Cart>();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(Cart cart) {
        Cart cartToReturn = cart;
        boolean found = false;

        for (int i = 0 ; i < this.cart.size() ; i++) {
            if (this.cart.get(i).getUserId() == cart.getUserId() && this.cart.get(i).getProductId() == cart.getProductId()) {
                for (int j = 0; j < this.product.size(); j++) {
                    if (this.product.get(j).getId() == cart.getProductId()) {
                        if ((this.cart.get(i).amount + 1) <= this.product.get(j).getStock()) {
                            this.cart.get(i).amount++;
                        }
                    }
                }
                cartToReturn = this.cart.get(i);
                found = true;
                break;
            }
        }
        if (found == false) {
            this.cart.add(cart);
        }
        System.out.println("Successfully added or updated a cart");
        return Response.ok(cartToReturn).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public ShoppingCart [] getShoppingCart(@PathParam("userId") long userId) {
        System.out.println(userId);
        ShoppingCart [] shoppingCarts;
        shoppingCarts = new ShoppingCart[this.cart.size()];

        for (int i = 0 ; i < shoppingCarts.length; i++) {
            for (int j = 0 ; j < this.product.size(); j++) {
                if (this.cart.get(i).getProductId() == this.product.get(j).getId()) {
                    shoppingCarts[i] = new ShoppingCart(this.product.get(j), this.cart.get(i));
                }
            }
        }

        return shoppingCarts;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userId}/{productId}")
    public void deleteCart(@PathParam("userId") long userId, @PathParam("productId") long productId) {
        System.out.println("Delete resource!");
        for (int i = 0 ; i < this.cart.size(); i++) {
            if (this.cart.get(i).getProductId() == productId) {
                if (this.cart.get(i).getUserId() == userId) {
                    System.out.println("Product Id:\t" + this.cart.get(i).getProductId());
                    System.out.println("User Id:\t" + this.cart.get(i).getUserId());
                    System.out.println("param User Id:\t" + userId);
                    System.out.println("param Product Id:\t" + productId);
                    this.cart.remove(i);
                }
            }
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public void deleteAllShoppingCartItems(@PathParam("userId") long userId) {
        System.out.println("Delete all carts belonging to user!");


        cart.removeIf( item -> item.getUserId() == userId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userId}/{productId}")
    public Response updateCart(@PathParam("userId") long userId, @PathParam("productId") long productId, Cart cart) {
        System.out.println("Update resource!");
        for (int i = 0 ; i < this.cart.size(); i++) {
            if (this.cart.get(i).getProductId() == productId) {
                if (this.cart.get(i).getUserId() == userId) {
                    this.cart.remove(i);
                    this.cart.add(cart);
                }
            }
        }
        return Response.ok(cart).build();
    }
}
