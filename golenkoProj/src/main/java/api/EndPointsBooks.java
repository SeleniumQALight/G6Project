package api;

public interface EndPointsBooks {
    String BASE_URL_BOOKS= "https://demoqa.com";
    String LOGIN_BOOKS = BASE_URL_BOOKS + "/Account/v1/Login";
    String DELETE_BOOKS = BASE_URL_BOOKS + "/BookStore/v1/Books?UserId={userId}";
    String BOOKS = BASE_URL_BOOKS + "/BookStore/v1/Books";

}
