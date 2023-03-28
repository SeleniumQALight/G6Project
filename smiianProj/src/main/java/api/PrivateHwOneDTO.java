package api;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    int baseCurrency;
    String baseCurrencyLit;
    ExchangeRateDTO exchangeRate;

}
