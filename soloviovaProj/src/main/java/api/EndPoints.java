package api;


public interface EndPoints {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";

    String EXCHANGE_RATE_PB = "https://api.privatbank.ua/p24api/exchange_rates";
    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";

}
