package api;

public class PrivatBankDTO {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;

    ExchangeRateDTO[] exchangeRate;




    public PrivatBankDTO() {
    }

    public PrivatBankDTO(String bank, String baseCurrencyLit, ExchangeRateDTO[] exchangeRate) {
        this.bank = bank;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRateDTO[] getExchangeRate() {

        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateDTO[] exchangeRate) {

        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }





}
