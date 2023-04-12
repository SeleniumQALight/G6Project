package api.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CurrencyPrivatDTO {

    private String ccy;
    private String baseCcy;
    private String buy;
    private String sale;
}
