package api.dto.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookRespHwTwoDemoqaDTO {
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
