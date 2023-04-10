package api;

public interface EndPoints {
    String baseUrl = "https://aqa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";

    String PB_EXCHANGE_RATES_URL = "https://api.privatbank.ua/p24api/exchange_rates";
    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";
}
