package api.dto.booksDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksDTO {
    String isbn;
    String title;
    String subTitle;
    String author;
    String publish_date;
    String publisher;
    Integer pages;
    String description;
    String website;
}
