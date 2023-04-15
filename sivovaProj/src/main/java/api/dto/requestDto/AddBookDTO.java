package api.dto.requestDto;


import api.ExchangeRateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookDTO {
    String userId;
    List<CollectionOfIsbnsDTO> collectionOfIsbns;


}
