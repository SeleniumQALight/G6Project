package api.dto.requestDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostDto {
    String title;
    String select1;
    String body;
    String token;
    String uniquePost;
}
