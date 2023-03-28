package api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    @JsonProperty("_id")
    String id;
    String title;
    String body;
    String select1;
    String uniquePost;
    String createdDate;
    AuthorDTO author;
    Boolean isVisitorOwner;

//    public PostDTO(){
//
//    }

//    public PostDTO(String title, String body, String select1, String uniquePost, AuthorDTO author, Boolean isVisitorOwner) {
//        this.title = title;
//        this.body = body;
//        this.select1 = select1;
//        this.uniquePost = uniquePost;
//        this.author = author;
//        this.isVisitorOwner = isVisitorOwner;
//    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public String getSelect1() {
//        return select1;
//    }
//
//    public void setSelect1(String select1) {
//        this.select1 = select1;
//    }
//
//    public String getUniquePost() {
//        return uniquePost;
//    }
//
//    public void setUniquePost(String uniquePost) {
//        this.uniquePost = uniquePost;
//    }
//
//    public String getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(String createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public AuthorDTO getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(AuthorDTO author) {
//        this.author = author;
//    }
//
//    public Boolean getIsVisitorOwner() {
//        return isVisitorOwner;
//    }
//
//    public void setIsVisitorOwner(Boolean visitorOwner) {
//        isVisitorOwner = visitorOwner;
//    }
//
//    @Override
//    public String toString() {
//        return "PostDTO{" +
//                "_id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", body='" + body + '\'' +
//                ", select1='" + select1 + '\'' +
//                ", uniquePost='" + uniquePost + '\'' +
//                ", createdDate='" + createdDate + '\'' +
//                ", author=" + author +
//                ", isVisitorOwner=" + isVisitorOwner +
//                '}';
//    }
}
