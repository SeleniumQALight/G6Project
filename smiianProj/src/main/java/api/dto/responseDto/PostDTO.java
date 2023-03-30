package api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter     //  анотації від Lombok замість Геттерів
@Setter     //  ...сеттерів
@ToString   //  ...
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostDTO {    // з кейса "ApiTest" отримуємо відповідь і з неї беремо поля, які і описуємо

    @JsonProperty("_id")    //  JackSon бібліотека. Через символ "_" прописуємо. Щоб Java сприймала правильно
    String id;
    String title;
    String body;
    String select1;

//    public PostDTO(){             //  це те саме що і анотація вище "@NoArgsConstructor"
//
//    }

//    public PostDTO(String title, String body, String select1,
//                   String uniquePost, AuthorDTO author, boolean isVisitorOwner) {   // ПКМ / Generate / Constructor
//        this.title = title;
//        this.body = body;
//        this.select1 = select1;
//        this.uniquePost = uniquePost;                                //  замість цієї групи коду є анотація "@Builder"  ! ! ! ! ! ! ! ! ! !
//        this.author = author;
//        this.isVisitorOwner = isVisitorOwner;
//    }



    String uniquePost;
    String createdDate;
    AuthorDTO author;       //  описуємо далі, як окремий клас "AuthorDTO"
    Boolean isVisitorOwner;



//    public String getId() {         //   ПКМ / Generate / getter and setter   генерується окремо після додавання @JsonProperty
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//    public String getTitle() {       //   ПКМ / Generate / getter and setter      і так для всіх
//        return title;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//
//    public String getBody() {
//        return body;
//    }
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//
//    public String getSelect1() {
//        return select1;
//    }
//    public void setSelect1(String select1) {
//        this.select1 = select1;
//    }
//
//
//    public String getUniquePost() {
//        return uniquePost;
//    }
//    public void setUniquePost(String uniquePost) {
//        this.uniquePost = uniquePost;
//    }
//
//
//    public String getCreatedDate() {
//        return createdDate;
//    }
//    public void setCreatedDate(String createdDate) {
//        this.createdDate = createdDate;
//    }
//
//
//    public AuthorDTO getAuthor() {
//        return author;
//    }
//    public void setAuthor(AuthorDTO author) {
//        this.author = author;
//    }
//
//
//    public boolean getIsVisitorOwner() {
//        return isVisitorOwner;
//    }
//    public void setIsVisitorOwner(boolean visitorOwner) {
//        isVisitorOwner = visitorOwner;
//    }
//
//    @Override
//    public String toString() {            //   ПКМ / Generate / toString
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
