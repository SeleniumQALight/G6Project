package api.dto.requestDto;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostDTO {
    String title;
    String body;
    String select1;
    String uniquePost;
    String token;
}
