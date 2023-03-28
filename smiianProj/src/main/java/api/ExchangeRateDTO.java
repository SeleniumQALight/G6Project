package api;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExchangeRateDTO {

    String baseCurrency;
    String currency;
}
