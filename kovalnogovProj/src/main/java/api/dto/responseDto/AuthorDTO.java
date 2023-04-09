package api.dto.responseDto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    public String username;
    public String avatar;

/*    public AuthorDTO() {

    }
    public AuthorDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }*/
}
