package br.com.accenture.wallet.transaction.domain.model;

public class BalanceModel {

    private String account;

    private Double value;

    private String lastUpdate;

    public BalanceModel() {
        this.account = "";
        this.value = 0.0d;
    }

    public String getAccount() { return account; }
    public void setAccount(String account) {
        this.account = account;
    }

    public Double getValue() { return value; }
    public void setValue(Double value) {
        this.value = value;
    }

    public String getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
