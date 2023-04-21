package api;

public interface EndPointsBookShop {
    String baseUrlBook ="https://demoqa.com";
    String LOGIN_BOOK = baseUrlBook + "/Account/v1/Login";
    String BOOK_STORE = baseUrlBook + "/BookStore/v1/Books";
    String USER_BOOK = baseUrlBook + "/Account/v1/User/{0}";
}
