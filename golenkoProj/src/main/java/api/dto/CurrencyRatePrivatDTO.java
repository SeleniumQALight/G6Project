package api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRatePrivatDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}
