package API;

public interface EndpointPrivatBank {
    String baseUrl = "https://api.privatbank.ua/p24api";
    String exchngeRate = baseUrl + "/exchange_rates";
    String EXCHANGE_RATES = baseUrl + "/pubinfo";
}
