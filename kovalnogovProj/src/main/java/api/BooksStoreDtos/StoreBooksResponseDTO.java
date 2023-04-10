package api.BooksStoreDtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "books"
})
@Getter
public class StoreBooksResponseDTO {
    @JsonProperty("books")
    public List<Book> books;
}
