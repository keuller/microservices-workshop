package br.com.accenture.wallet.onboarding.domain;

public class BalanceModel {
    private String customer;
    private String account;
    private Double value;

    public String getCustomer() { return customer; }
    public BalanceModel setCustomer(String value) { this.customer = value; return this; }

    public String getAccount() { return account; }
    public BalanceModel setAccount(String value) { this.account = value; return this; }

    public Double getValue() { return value; }
    public BalanceModel setValue(Double value) { this.value = value; return this; }

}
