package api.dto.books;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginBooksDTO {
    String createdDate;
    String expires;
    Boolean isActive;
    String password;
    String token;
    String userId;
    String username;
}
