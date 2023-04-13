package api;

public interface EndPointsPrivatBank {
    String baseUrl = "https://api.privatbank.ua/p24api";
    String CURRENCY_KURS = baseUrl + "pubinfo?json&exchange&coursid=5";
}
