package api.dto.responseDto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivatbankDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}
