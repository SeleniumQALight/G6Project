package api.dto.bookStoreDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestAddBookDTO {
    String userId;
    List<CollectionOfBookDTO> collectionOfIsbns;
}
