package api.dto.requestDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddBookReqHwTwoDemoqaDTO {

    String userId;
    IsbnReqHwTwoDemoqaDTO collectionOfIsbns;
}
