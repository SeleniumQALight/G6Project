package bookstoreApi;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddedBooksDTO {
    String userId;
    List<UsersIsbnsOfBooks> usersIsbnsOfBooksList;
}
