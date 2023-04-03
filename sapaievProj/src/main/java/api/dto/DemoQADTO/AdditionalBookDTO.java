package api.dto.DemoQADTO;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalBookDTO {
    String userId;
    CollectionOfIsbnsDTO[] collectionOfIsbns;
}
