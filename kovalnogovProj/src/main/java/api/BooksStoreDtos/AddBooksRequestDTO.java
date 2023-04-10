package api.BooksStoreDtos;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBooksRequestDTO {
    public String userId;
    public List<CollectionOfIsbn> collectionOfIsbns;
}
