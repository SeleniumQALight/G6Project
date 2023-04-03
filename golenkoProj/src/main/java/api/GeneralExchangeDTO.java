package api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralExchangeDTO {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    List<ExchangeRateDTO> exchangeRate;

}
