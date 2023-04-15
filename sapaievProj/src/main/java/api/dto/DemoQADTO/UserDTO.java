package api.dto.DemoQADTO;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    Boolean isActive;
}
