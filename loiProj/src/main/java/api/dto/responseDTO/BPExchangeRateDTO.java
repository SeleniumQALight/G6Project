package api.dto.responseDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BPExchangeRateDTO {
    String baseCurrency;
    String currency;
    Float saleRateNB;
    Float purchaseRateNB;
    Float saleRate;
    Float purchaseRate;
}
