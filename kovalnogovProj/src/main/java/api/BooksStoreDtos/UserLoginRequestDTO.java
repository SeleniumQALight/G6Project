package api.BooksStoreDtos;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDTO {
    public String userName;
    public String password;
}