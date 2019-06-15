package br.com.accenture.wallet.balance.domain;

import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Objects.nonNull;

public class BalanceModel {
    private static volatile DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private String id;
    private Double value;

    @NotBlank
    private String account;

    @NotBlank
    private String customer;

    private String lastUpdate;

    public BalanceModel() {}

    public String getId() {
        return id;
    }
    public BalanceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getAccount() { return account; }
    public BalanceModel setAccount(String value) {
        this.account = value;
        return this;
    }

    public String getCustomer() { return customer; }
    public BalanceModel setCustomer(String value) {
        this.customer = value;
        return this;
    }

    public Double getValue() {
        return value;
    }
    public BalanceModel setValue(Double value) {
        this.value = value;
        return this;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
    public BalanceModel setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public static BalanceModel fromEntity(final Balance bean) {
        final BalanceModel model = new BalanceModel();
        model.setId(bean.getId())
            .setAccount(bean.getAccountId())
            .setCustomer(bean.getCustomerId())
            .setValue(bean.getValue())
            .setLastUpdate(toLastUpdate(bean.getLastUpdate()));
        return model;
    }

    public Balance toEntity() {
        final Balance balance = new Balance();
        if (nonNull(id) && !"".equals(id)) balance.setId(id);
        balance.setAccountId(account)
            .setCustomerId(customer)
            .setValue(value);
        return balance;
    }

    private static String toLastUpdate(Date value) {
        return df.format(value);
    }

}
