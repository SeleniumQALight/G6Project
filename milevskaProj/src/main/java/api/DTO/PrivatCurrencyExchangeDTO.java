package api.DTO;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrivatCurrencyExchangeDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}
