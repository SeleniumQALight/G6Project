package api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PBExchangeDTO {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    PBExchangeRatesDTO[] exchangeRate;
}
