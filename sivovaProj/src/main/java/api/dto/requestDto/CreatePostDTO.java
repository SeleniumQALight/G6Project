package api.dto.requestDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
