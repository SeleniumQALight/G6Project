package api.BooksStoreDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
@Getter
public class UserProfile {
    @JsonProperty("userId")
    public String  userId;
    @JsonProperty("username")
    public String username;
    @JsonProperty("books")
    public List<Book> books;
}
