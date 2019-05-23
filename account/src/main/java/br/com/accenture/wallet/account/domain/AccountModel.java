package br.com.accenture.wallet.account.domain;

import javax.validation.constraints.NotBlank;
import static java.util.Objects.nonNull;

public class AccountModel {
    private final String PAYMENT = "payment";
    private final String ESCROW = "escrow";

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

    public AccountModel fromEntity(Account bean) {
        final AccountModel model = new AccountModel();
        model.setId(bean.getId())
            .setActive(bean.isActive())
            .setCustomer(bean.getCustomerId())
            .setType(toType(bean.getType()));
        return model;
    }

    public Account toEntity() {
        final Account account = new Account().setActive(active);
        if (nonNull(id) && !"".equals(id)) account.setId(id);
        if (nonNull(customer) && !"".equals(customer)) account.setCustomerId(customer);
        if (nonNull(type) && !"".equals(type)) account.setType(toType(type));
        return account;
    }

    private Integer toType(String value) {
        switch(value) {
            case PAYMENT: return 1;
            case ESCROW: return 2;
            default: return null;
        }
    }

    private String toType(Integer value) {
        switch(value) {
            case 1: return PAYMENT;
            case 2: return ESCROW;
            default: return null;
        }
    }

}
