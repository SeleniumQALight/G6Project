package api.dto.requestDto;

import api.dto.responseDto.AuthorDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddBookToUserReqHwTwoDemoqaDTO {

    String userId;
    IsbnHwTwoDemoqaDTO isbn;
}
