package api;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PrivatBankDTO {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRateDTO> exchangeRate;
}





