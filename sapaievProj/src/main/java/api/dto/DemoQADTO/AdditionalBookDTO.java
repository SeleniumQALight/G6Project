package api.dto.DemoQADTO;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalBookDTO {
    String userId;
    List<CollectionOfIsbnsDTO> collectionOfIsbns;
}
