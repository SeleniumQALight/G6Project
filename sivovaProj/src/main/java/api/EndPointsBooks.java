package api;

public interface EndPointsBooks {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String BOOKSTORE = baseUrl + "/BookStore/v1/Books";
    String GENERATE_TOKEN = baseUrl + "/Account/v1/GenerateToken";
    String USER_PROFILE = baseUrl + "/Account/v1/User/{0}";

}
