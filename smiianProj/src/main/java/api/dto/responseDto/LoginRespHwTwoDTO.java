package api.dto.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginRespHwTwoDTO {

    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    Boolean isActive;

}