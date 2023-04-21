package api.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CurrencyPrivatDTO {

    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
