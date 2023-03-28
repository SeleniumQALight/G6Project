package api.privateBank;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonPropertyOrder({
        "date",
        "bank",
        "baseCurrency",
        "baseCurrencyLit",
        "exchangeRate"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesInfo {

    @JsonProperty("date")
    public String date;
    @JsonProperty("bank")
    public String bank;
    @JsonProperty("baseCurrency")
    public Integer baseCurrency;
    @JsonProperty("baseCurrencyLit")
    public String baseCurrencyLit;
    @JsonProperty("exchangeRate")
    public List<ExchangeRate> exchangeRate;

}
