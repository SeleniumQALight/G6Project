package api.BooksStoreDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "isbn",
        "title",
        "subTitle",
        "author",
        "publish_date",
        "publisher",
        "pages",
        "description",
        "website"
})
@Getter
public class Book {

    @JsonProperty("isbn")
    public String isbn;
    @JsonProperty("title")
    public String title;
    @JsonProperty("subTitle")
    public String subTitle;
    @JsonProperty("author")
    public String author;
    @JsonProperty("publish_date")
    public String publishDate;
    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("pages")
    public Integer pages;
    @JsonProperty("description")
    public String description;
    @JsonProperty("website")
    public String website;

}