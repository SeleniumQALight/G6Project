package api.dto.privatBankDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainExchangeDTO {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    List<ExchangeDTO> exchangeRate;
}
