package api.dto.requestDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddListOfBooksDTO {
    String userId;
    CollectionOfIsbnsDTO[] collectionOfIsbns;
}
