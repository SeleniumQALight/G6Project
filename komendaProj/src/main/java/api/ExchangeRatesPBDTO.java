package api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRatesPBDTO {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    ExchangeRatePBuahDTO[] exchangeRate;

}
