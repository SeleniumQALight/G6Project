package api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CurrencyDTO {
    String data;
    String bank;
    Integer baseCurrency;
    String basCurrencyLit;
    ExchangeRateDTO exchange;

}
