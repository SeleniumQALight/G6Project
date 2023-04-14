package api.dto.responseDto;

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
        Float saleRateNB;
        Float purchaseRateNB;
        Float saleRate;
        Float purchaseRate;
}
