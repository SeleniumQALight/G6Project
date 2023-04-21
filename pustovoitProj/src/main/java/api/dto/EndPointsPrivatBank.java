package api.dto;

public interface EndPointsPrivatBank {
    String baseURLPrivatUI = "https://privatbank.ua";
    String baseURLPrivatAPI = "https://api.privatbank.ua/p24api";
    String exchengeRateURL =baseURLPrivatAPI + "/exchenge_rate";
    String EXCHENGE_RATE_URL = baseURLPrivatAPI + "/pubinfo";

}
