package api;

import lombok.*;

@Data//includes getter, setter, some builders
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    String username;
    String avatar;

}
