package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String userEmail = "email";
    private String userPassword = "pass";

    public User() {}

    public User(String email, String password) {
        this.userEmail = email;
        this.userPassword = password;
    }

    @JsonProperty
    public String getUserEmail() {
        return this.userEmail;
    }

    @JsonProperty
    public String getUserPassword() {
        return this.userPassword;
    }

}