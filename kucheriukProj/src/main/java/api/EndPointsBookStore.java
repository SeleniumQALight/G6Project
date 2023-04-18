package api;

public interface EndPointsBookStore {
    String baseUrl = "https://demoqa.com";
    String LOGIN = baseUrl + "/Account/v1/Login";
    String BOOKSTORE = baseUrl + "/BookStore/v1/Books";
    String TOKEN = baseUrl + "/Account/v1/GenerateToken";
    String USER_FILE = baseUrl + "/Account/v1/User/{0}";
}
