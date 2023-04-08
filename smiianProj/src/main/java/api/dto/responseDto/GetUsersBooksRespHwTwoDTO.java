package api.dto.responseDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class GetUsersBooksRespHwTwoDTO {

    List<GetUsersBooksInnerRespHwTwoDTO> books;

}
