package api.dto.books;

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
    List<IsbnBooksDTO> collectionOfIsbns;
}
