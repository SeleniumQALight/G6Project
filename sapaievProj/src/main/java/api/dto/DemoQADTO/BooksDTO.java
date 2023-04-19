package api.dto.DemoQADTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksDTO {
    BookDTO[] books;
}
