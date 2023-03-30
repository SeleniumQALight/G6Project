package api.dto.responseDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PBExchangeDTO {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    BPExchangeRateDTO[] exchangeRate;
}
