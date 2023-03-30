package api;

public interface EndPoints {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";

    String EXCHANGE_RATE_PB = "https://api.privatbank.ua/p24api/exchange_rates";
}
