package api.dto.responseDto;

import api.dto.requestDto.IsbnReqHwTwoDemoqaDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GetAllBooksRespHwTwoDemoqaDTO {

    String userId;
    String username;
    List<BookRespHwTwoDemoqaDTO> books;
}
