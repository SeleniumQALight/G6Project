package api;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PrivatExcRateDTO {
        String date;
        String bank;
        int baseCurrency;
        String baseCurrencyLit;
        ExchangeRateDTO[] exchangeRate;

        // Getters and setters
    }

