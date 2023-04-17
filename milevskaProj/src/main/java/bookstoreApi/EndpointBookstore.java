package bookstoreApi;

public interface EndpointBookstore {
    String baseUrl = "https://demoqa.com";
    String login = baseUrl + "/Account/v1/Login";
    String deleteBooks = baseUrl + "/BookStore/v1/Books?UserId={0}";
    String books = baseUrl + "/BookStore/v1/Books";
    String userBooks = baseUrl + "/Account/v1/User/{userId}";
}
