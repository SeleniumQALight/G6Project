package api.BooksStoreDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBooksResponseDTO {
    @JsonProperty("books")
    public List<CollectionOfIsbn> collectionOfIsbns;
}
