package api.dto.bookStoreDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    String token;
    String userId;
    String username;
    String password;
    String expires;
    String created_date;
    Boolean isActive;
}
