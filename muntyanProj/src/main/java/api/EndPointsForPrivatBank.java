package api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface EndPointsForPrivatBank {
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formattedDate = currentDate.format(formatter);



    final String baseUrl = "https://api.privatbank.ua";
    String GET_CURRENCY = baseUrl + "/p24api/exchange_rates";


}
