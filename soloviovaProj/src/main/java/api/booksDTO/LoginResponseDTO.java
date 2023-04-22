package api.booksDTO;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponseDTO {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    String created_date;
    String isActive;
}
