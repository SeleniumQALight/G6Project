package api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //  так само як гетер s cетер
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthorDTO {
    String username;
    String avatar;



}
