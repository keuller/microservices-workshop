package br.com.accenture.wallet.balance.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import static java.util.Objects.nonNull;

public class BalanceChangeModel {
    @NotBlank
    private String account;

    @NotBlank
    @Pattern(regexp = "credit|debit")
    private String operation;

    private Double value;

    public BalanceChangeModel() {
        this.account = "";
        this.value = 0.0d;
    }

    public String getAccount() { return account; }
    public BalanceChangeModel setAccount(String value) {
        if (nonNull(value) && !"".equals(value)) this.account = value;
        return this;
    }

    public String getOperation() { return operation; }
    public BalanceChangeModel setOperation(String value) {
        if (nonNull(value) && !"".equals(value)) this.operation = value;
        return this;
    }

    public Double getValue() { return value; }
    public BalanceChangeModel setValue(Double value) { this.value = value; return this; }

}
