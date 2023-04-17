package api.dto.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginBooksDTO {
    @JsonProperty("created_date")
            String createdDate;
            String expires;
            String isActive;
            String token;
            String userId;
            String username;
            String password;

}
