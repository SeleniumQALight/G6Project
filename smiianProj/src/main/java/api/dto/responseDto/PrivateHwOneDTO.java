package api.dto.responseDto;

import api.dto.responseDto.ExchangeRateDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PrivateHwOneDTO {

    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    ExchangeRateDTO[] exchangeRate;

}
