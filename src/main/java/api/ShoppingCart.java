package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {
    private Product product;
    private Cart cart;

    public ShoppingCart() {
        // Jackson deserialization
    }

    public ShoppingCart(Product product, Cart cart) {
        this.product = product;
        this.cart = cart;
    }

    @JsonProperty
    public Cart getCarts() {
        return cart;
    }

    @JsonProperty
    public Product getProducts() { return product; }
}
