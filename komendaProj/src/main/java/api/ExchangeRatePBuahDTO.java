package api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExchangeRatePBuahDTO {
    String baseCurrency;
    String currency;
    Float saleRateNB;
    Float purchaseRateNB;
    Float saleRate;
    Float purchaseRate;

}
