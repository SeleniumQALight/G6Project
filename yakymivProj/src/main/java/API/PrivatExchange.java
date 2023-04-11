package API;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivatExchange {
    String date;
    String bank;
    int baseCurrency;
    String baseCurrencyLit;
    List<PrivatExchangeRateDTO> exchangeRate;

}