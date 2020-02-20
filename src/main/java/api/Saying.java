package api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {
    private long id = 1;
    private String name = "content";
    private String description = "desc";

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content, String description) {
        this.id = id;
        this.name = content;
        this.description = description;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

}
