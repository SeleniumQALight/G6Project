package api.dto.requestDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddBookReqHwTwoDemoqaDTO {

    String userId;
    List<IsbnReqHwTwoDemoqaDTO> collectionOfIsbns;
}
