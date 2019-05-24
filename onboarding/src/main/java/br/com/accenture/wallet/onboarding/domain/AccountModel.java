package br.com.accenture.wallet.onboarding.domain;

public class AccountModel {
    private String id;
    private String customer;
    private String type;

    public AccountModel() {
    }

    public String getId() {
        return id;
    }
    public AccountModel setId(String value) {
        this.id = value;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public AccountModel setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getType() {
        return type;
    }

    public AccountModel setType(String type) {
        this.type = type;
        return this;
    }
}
