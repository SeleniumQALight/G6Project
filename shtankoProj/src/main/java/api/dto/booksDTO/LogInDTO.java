package api.dto.booksDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogInDTO {
    String created_date;
    String expires;
    Boolean isActive;
    String password;
    String token;
    String userId;
    String username;
}
