package api;

public interface BooksStoreApi {
    public String BASEURI="https://demoqa.com";
    public String GENERATE_TOKEN = BASEURI+"/Account/v1/GenerateToken";
    public String LOGIN = BASEURI+"/Account/v1/Login";
    public String BOOKS = BASEURI+"/BookStore/v1/Books";
    public String DELETE_BOOKS = BASEURI+"/BookStore/v1/Books?UserId={UserId}";
    public String USER_PROFILE = BASEURI+"/Account/v1/User/{UserId}";
}
