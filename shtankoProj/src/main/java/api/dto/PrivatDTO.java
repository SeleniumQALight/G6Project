package api.dto;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivatDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}
