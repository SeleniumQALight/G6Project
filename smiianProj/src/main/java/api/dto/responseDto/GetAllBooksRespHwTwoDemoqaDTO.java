package api.dto.responseDto;

import api.dto.requestDto.IsbnReqHwTwoDemoqaDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GetAllBooksRespHwTwoDemoqaDTO {

    String userId;
    String username;
    BookRespHwTwoDemoqaDTO books;
}
