package api.dto.responseDto;

import lombok.*;

public class ExchangeKursDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class EcxhangeKursDTO {
        private String ccy;
        private String baseCcy;
        private String buy;
        private String sale;

    }

}
