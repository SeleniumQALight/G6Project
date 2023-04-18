package api;

public interface EndpointPrivatBank {
    String baseUrl = "https://api.privatbank.ua";
    String apiUrl = baseUrl + "/p24api";
    String exchngeRate = apiUrl + "/exchange_rates";
    String exchangeAPI = baseUrl + "/p24api/pubinfo";
}
