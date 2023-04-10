package api.BooksStoreDtos;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "username",
        "password",
        "token",
        "expires",
        "created_date",
        "isActive"
})
@Getter
public class UserLoginResponseDTO {
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;
    @JsonProperty("token")
    public String token;
    @JsonProperty("expires")
    public String expires;
    @JsonProperty("created_date")
    public String createdDate;
    @JsonProperty("isActive")
    public Boolean isActive;

}
