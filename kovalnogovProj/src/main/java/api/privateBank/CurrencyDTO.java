package api.privateBank;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ccy",
        "base_ccy",
        "buy",
        "sale"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CurrencyDTO {
    @JsonProperty("ccy")
    public String ccy;
    @JsonProperty("base_ccy")
    public String baseCcy;
    @JsonProperty("buy")
    public String buy;
    @JsonProperty("sale")
    public String sale;

}
