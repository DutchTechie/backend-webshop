package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private final String name, description, imagePath;
    private long id;
    private double price;
    private int stock;

    public Product(
            long id,
            String name,
            String description,
            String imagePath,
            double price,
            int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.stock = stock;
    }

    @JsonProperty
    public long getId() {
        return this.id;
    }

    @JsonProperty
    public String getName() {
        return this.name;
    }

    @JsonProperty
    public String getDescription() {
        return this.description;
    }

    @JsonProperty
    public String getImagePath() {
        return this.imagePath;
    }

    @JsonProperty
    public double getPrice() {
        return this.price;
    }

    @JsonProperty
    public int getStock() {
        return this.stock;
    }
}
