package api.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestDTO {
    public String select1;
    public String title;
    public String body;
    public String uniquePost;
    public String token;
}