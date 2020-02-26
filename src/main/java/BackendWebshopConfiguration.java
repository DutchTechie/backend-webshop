import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

public class BackendWebshopConfiguration extends Configuration {
    @NotEmpty
    private String template = "Hello, ";

    @NotEmpty
    private String defaultName = "Stranger";

    @NotEmpty
    private String description = "Description";

    @NotEmpty
    private String userEmail = "Stranger";

    @NotEmpty
    private String userPassword = "Description";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public String getUserEmail() {
        return this.userEmail;
    }

    @JsonProperty
    public String getUserPassword() {
        return this.userPassword;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String desc) {
        this.description = desc;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
