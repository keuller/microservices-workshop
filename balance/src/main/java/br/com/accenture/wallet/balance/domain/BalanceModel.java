package br.com.accenture.wallet.balance.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BalanceModel {
    private volatile DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private String id;
    private Double value;
    private String lastUpdate;

    public BalanceModel() {}

    public String getId() {
        return id;
    }

    public BalanceModel setId(String id) {
        this.id = id;
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

    public BalanceModel fromEntity(final Balance bean) {
        this.setId(bean.getId())
            .setValue(bean.getValue())
            .setLastUpdate(toLastUpdate(bean.getLastUpdate()));
        return this;
    }

    private String toLastUpdate(Date value) {
        return df.format(value);
    }

}
