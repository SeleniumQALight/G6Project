package api;

public interface EndPointsPrivat {
    String baseUrlPrivat = "https://api.privatbank.ua/";
    String getExchangeRates = baseUrlPrivat + "p24api/exchange_rates?json&date={0}";
}
