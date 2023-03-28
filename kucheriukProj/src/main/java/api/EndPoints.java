package api;

public interface EndPoints {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";

    String PB_EXCHANGE_RATES_URL = "https://api.privatbank.ua/p24api/exchange_rates";
}
