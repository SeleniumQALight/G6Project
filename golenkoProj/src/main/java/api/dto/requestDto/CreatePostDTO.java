package api.dto.requestDto;

import lombok.*;

@Data
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
