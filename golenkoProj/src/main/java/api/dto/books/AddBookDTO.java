package api.dto.books;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookDTO {
    String userId;
    List<IsbnDTO> collectionOfIsbns;

}
