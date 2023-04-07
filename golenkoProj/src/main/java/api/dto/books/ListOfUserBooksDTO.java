package api.dto.books;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListOfUserBooksDTO {
    String userId;
    String username;
    BookDTO[] books;
}
