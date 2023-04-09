package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivatExchange {
    String date;
    String bank;
    int baseCurrency;
    String baseCurrencyLit;
    PrivatExchangeRateDTO[] exchangeRate;

}
