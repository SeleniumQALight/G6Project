package api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivateBankDTO {
String date;
String bank;
Integer baseCurrency;
String baseCurrencyLit;
ExchangeRateDTO[] exchangeRate;
}
