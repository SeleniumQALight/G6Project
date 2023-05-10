package api.dto.responseDto;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeKursDto {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;

}
