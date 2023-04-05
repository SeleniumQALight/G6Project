package api.dto.responseDto;

import api.dto.requestDto.IsbnReqHwTwoDemoqaDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddBookRespHwTwoDemoqaDTO {

    IsbnRespHwTwoDemoqaDTO books;
}
