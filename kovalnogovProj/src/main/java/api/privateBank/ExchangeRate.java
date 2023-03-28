package api.privateBank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


@JsonPropertyOrder({
        "baseCurrency",
        "currency",
        "saleRateNB",
        "purchaseRateNB",
        "saleRate",
        "purchaseRate"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExchangeRate {
    @JsonProperty("baseCurrency")
    public String baseCurrency;
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("saleRateNB")
    public Double saleRateNB;
    @JsonProperty("purchaseRateNB")
    public Double purchaseRateNB;
    @JsonProperty("saleRate")
    public Double saleRate;
    @JsonProperty("purchaseRate")
    public Double purchaseRate;

}
