package api;

public class BPExchangeRateDTO {
    String baseCurrency;
    String currency;
    Float saleRateNB;
    Float purchaseRateNB;
    Float saleRate;
    Float purchaseRate;

    public BPExchangeRateDTO() {
    }

    public BPExchangeRateDTO(String baseCurrency, String currency) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(Float saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public Float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(Float purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public Float getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Float saleRate) {
        this.saleRate = saleRate;
    }

    public Float getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Float purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "BPExchangeRateDTO{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }
}
