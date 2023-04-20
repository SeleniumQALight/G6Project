package API.DTO.responseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivatDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}