package api.dto.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CurrencyRespPrivatDTO {
    String ccy;
    String base_ccy;
    String buy;
    String sale;
}
