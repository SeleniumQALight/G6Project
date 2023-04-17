package api;

public interface EndPointsPrivat {
    String baseUrl = "https://api.privatbank.ua/p24api";
    String CURRENCY_RATE_ON_DATE = baseUrl+ "/exchange_rates";
    String CURRENCY_EXCHANGE_RATE_BASED_ON_ID = baseUrl + "/pubinfo";
}
