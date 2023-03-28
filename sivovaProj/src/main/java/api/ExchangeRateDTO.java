package api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ExchangeRateDTO {
    String baseCurrency;
    String currency;
    Integer saleRateNB;
    Integer purchaseRateNB;
    Integer saleRate;
    Integer purchaseRate;

}
