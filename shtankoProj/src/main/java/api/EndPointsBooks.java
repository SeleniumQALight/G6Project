package api;

public interface EndPointsBooks {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String BOOKS = baseUrl + "/BookStore/v1/Books";
    String USER_BOOKS = baseUrl + "/Account/v1/User/{userId}";
    String DELETE_BOOKS = baseUrl + "/BookStore/v1/Books";
}
