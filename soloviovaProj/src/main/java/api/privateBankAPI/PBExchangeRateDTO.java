package api.privateBankAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PBExchangeRateDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}
