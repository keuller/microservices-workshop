package br.com.accenture.wallet.account.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BalanceModel {
    @NotBlank
    private String account;

    @NotBlank
    private String operation;

    @NotNull
    private Double value;

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getValue() { return value; }
    public void setValue(Double value) {
        this.value = value;
    }

}
