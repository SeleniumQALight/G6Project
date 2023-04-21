package api.dto.bookStore;

import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListBooksDto {
    List<BooksDTO> books;
}