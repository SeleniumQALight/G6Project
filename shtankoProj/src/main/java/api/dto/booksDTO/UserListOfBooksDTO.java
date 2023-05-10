package api.dto.booksDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListOfBooksDTO {
    String userId;
    String username;
    BooksDTO[] books;
}
