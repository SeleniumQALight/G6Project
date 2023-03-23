package api;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public PostDTO() {
    }


    public PostDTO(String title, String body, String select1, String uniquePost, AuthorDTO author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select1 = select1;
        this.uniquePost = uniquePost;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }



    public String getid() {
        return id;
    }

    public void set_id(String id) {
        this.id = id;
    }







    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSelect1() {
        return select1;
    }

    public String getUniquePost() {
        return uniquePost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public void setUniquePost(String uniquePost) {
        this.uniquePost = uniquePost;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public void setIsVisitorOwner(boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }


    @Override
    public String toString() {
        return "PostDTO{" +
                "_id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select1='" + select1 + '\'' +
                ", uniquePost='" + uniquePost + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author=" + author +
                ", isVisitorOwner=" + isVisitorOwner +
                '}';
    }
}
