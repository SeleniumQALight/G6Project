package api.dto.booksDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBooksDTO {
    String userId;
    List<ISBNDTO> collectionOfIsbns;
}
