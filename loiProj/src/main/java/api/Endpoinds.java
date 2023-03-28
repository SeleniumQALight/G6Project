package api;

public interface Endpoinds {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";
    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";

    String PB_EXCHANGE_RATE_URL = "https://api.privatbank.ua/p24api/exchange_rates";
}
