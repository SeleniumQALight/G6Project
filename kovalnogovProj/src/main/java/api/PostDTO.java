package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDTO {

    @JsonProperty("_id")
    public  String id;
    public String title;
    public String body;
    public String select1 ;
    public String uniquePost;
    public String createdDate;
    public AuthorDTO author;
    public Boolean  isVisitorOwner;


    public PostDTO() {
    }


    public PostDTO(String id, String title, String body,  String uniquePost, AuthorDTO author, Boolean isVisitorOwner) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.uniquePost = uniquePost;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }


    @Override
    public String toString() {
        return "PostDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select1='" + select1 + '\'' +
                ", uniquePost='" + uniquePost + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author=" + author +
                ", isVisitorOwner=" + isVisitorOwner +
                '}';
    }

    public String get_id() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getUniquePost() {
        return uniquePost;
    }

    public void setUniquePost(String uniquePost) {
        this.uniquePost = uniquePost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public Boolean getVisitorOwner() {
        return isVisitorOwner;
    }

    public void setVisitorOwner(Boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }
}
