package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cart {
    private long userId = -1;
    private long productId = -1;
    public int amount = 0;

    public Cart() {
        // Jackson deserialization
    }

    public Cart(long userId, long productId, int amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    @JsonProperty
    public long getUserId() {
        return userId;
    }

    @JsonProperty
    public long getProductId() {
        return productId;
    }

    @JsonProperty
    public int getAmount() {
        return amount;
    }
}
