package api;

public interface EndPointsDemoqa {

    String baseUrlDemoqa = "https://demoqa.com";
    String LOGIN = baseUrlDemoqa + "/Account/v1/Login";
    String GET_ALL_BOOKS = baseUrlDemoqa + "/BookStore/v1/Books";
    String DELETE_ALL_BOOKS = baseUrlDemoqa + "/BookStore/v1/Books?UserId={userId}";
    String ADD_BOOK_TO_USER = baseUrlDemoqa + "/BookStore/v1/Books";
    String GET_USER_INFO = baseUrlDemoqa + "/Account/v1/User/{serId}";
}
