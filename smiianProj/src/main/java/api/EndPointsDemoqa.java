package api;

public interface EndPointsDemoqa {

    String baseUrlDemoqa = "https://demoqa.com";
    String LOGIN = baseUrlDemoqa + "/Account/v1/Login";
    String GET_ALL_BOOKS = baseUrlDemoqa + "/BookStore/v1/Books";
    String DELETE_ALL_BOOKS = baseUrlDemoqa + "/BookStore/v1/Books?UserId={userId}";
    String DELETE_BOOK = baseUrlDemoqa + "/BookStore/v1/Book";
}
