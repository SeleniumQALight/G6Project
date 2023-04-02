package api.dto.responseDto;


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
    String saleRateNB;
    String purchaseRateNB;
    String saleRate;
    String purchaseRate;
}
