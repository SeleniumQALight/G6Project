package bookstoreApi;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginBookstoreDTO {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    String isActive;
}
