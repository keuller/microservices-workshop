package br.com.accenture.wallet.account.domain;

import javax.validation.constraints.NotBlank;

public class AccountModel {
    private String id;

    @NotBlank
    private String type;

    @NotBlank
    private String customer;

    private boolean active;

    public AccountModel() {
    }

    public String getId() {
        return id;
    }
    public AccountModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }
    public AccountModel setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isActive() { return active; }
    public AccountModel setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getCustomer() { return customer; }
    public AccountModel setCustomer(String value) {
        this.customer = value;
        return this;
    }

    public static AccountModel fromEntity(Account bean) {
        final AccountModel model = new AccountModel();
        model.setId(bean.getId())
            .setActive(bean.isActive())
            .setCustomer(bean.getCustomerId())
            .setType(toType(bean.getType()));
        return model;
    }

    public Account toEntity() {
        return new Account()
            .setActive(active)
            .setId(id)
            .setCustomerId(customer)
            .setType(toType(type));
    }

    private Integer toType(String value) {
        switch(value) {
            case AccountType.PAYMENT: return 1;
            case AccountType.ESCROW: return 2;
            default: return null;
        }
    }

    private static String toType(Integer value) {
        switch(value) {
            case 1: return AccountType.PAYMENT;
            case 2: return AccountType.ESCROW;
            default: return null;
        }
    }

}
