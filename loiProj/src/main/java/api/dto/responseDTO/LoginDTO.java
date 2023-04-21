package api.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    @JsonProperty("created_date")
    private String createdDate;
    private String expires;
    private String isActive;
    private String password;
    private String token;
    private String userId;
    private String username;
}
