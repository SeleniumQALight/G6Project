package api.dto.books;

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
