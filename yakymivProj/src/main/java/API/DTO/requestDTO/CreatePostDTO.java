package API.DTO.requestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDTO {
    String title;
    String body;
    String select1;
    String uniquePost;
    String token;

}
