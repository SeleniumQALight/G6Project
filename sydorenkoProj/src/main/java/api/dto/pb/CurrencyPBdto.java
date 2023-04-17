package api.dto.pb;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyPBdto {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}