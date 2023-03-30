package api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PBExchangeRatesDTO {
    String baseCurrency;
    String currency;
    Float saleRateNB;
    Float purchaseRateNB;
    Float saleRate;
    Float purchaseRate;
}
