package api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CurrencyDTO {
    @JsonProperty("date")
    String date;
    @JsonProperty("bank")
    String bank;
    @JsonProperty("baseCurrency")
    Integer baseCurrency;
    @JsonProperty("baseCurrencyLit")
    String baseCurrencyLit;
    List<ExchangeRateDTO> exchangeRate;

}
