package api.dto.responseDto;

import api.dto.responseDto.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    @JsonProperty("_id")
    public  String id;
    public String title;
    public String body;
    public String select1 ;
    public String uniquePost;
    public String createdDate;
    public AuthorDTO author;
    public Boolean  isVisitorOwner;

}
