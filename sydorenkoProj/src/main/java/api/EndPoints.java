package api;

public interface EndPoints {
    String baseUrl = "https://aqa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";
    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";

    String BASE_URL_PB_API = "https://api.privatbank.ua/p24api";
    String EXCHANGE_RATES_API_PB = BASE_URL_PB_API + "/exchange_rates";

    String baseUrlPB = "https://api.privatbank.ua";
    String EXCHANGE_RATES_PB = baseUrlPB + "/p24api/exchange_rates?date={0}";
    String EXCHANGE_RATES_PB1 = baseUrlPB + "/p24api/exchange_rates";
    String CURRENCY_EXCHANGE = baseUrlPB + "/p24api/pubinfo?json&exchange&coursid=5";

    String baseUrlBook = "https://demoqa.com";
    String LOGIN_BOOK = baseUrlBook + "/Account/v1/Login";
    String BOOK_STORE = baseUrlBook + "/BookStore/v1/Books";
}
