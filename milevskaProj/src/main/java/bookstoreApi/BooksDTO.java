package bookstoreApi;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BooksDTO {
    BookDTO[] books;
}
