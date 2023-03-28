package api;

public interface EndPoints {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";   // кажемо: замість нуля підстави перший параметр, який будемо передавати

    String PRIVATE_API_22_03 = "https://api.privatbank.ua/p24api/exchange_rates?date=22.03.2022";
}
