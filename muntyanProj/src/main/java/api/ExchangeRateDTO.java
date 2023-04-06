package api;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateDTO {

        private String baseCurrency;
        private String currency;
        private double saleRateNB;
        private double purchaseRateNB;
        private Double saleRate;
        private Double purchaseRate;


        public static  ExchangeRateDTO createExchangeRateWithUAHAsDefault (String currency){
                return ExchangeRateDTO.builder()
                        .baseCurrency("UAH")
                        .currency(currency)
                        .build();
        }

}