package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private long id = -1;
    private String email = "";
    private String password = "";
    private boolean isAdmin = false;

    public User() {
        // Jackson deserialization
    }

    public User(long id, String email, String password, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @JsonProperty
    public long getId() {
        return this.id;
    }

    @JsonProperty
    public String getEmail() {
        return this.email;
    }

    @JsonProperty
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public boolean isAdmin() {
        return isAdmin;
    }
}
