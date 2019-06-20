package br.com.accenture.wallet.transaction.domain.model;

public class BalanceOperationModel {
    private final String operation;
    private String account;
    private Double value;

    public BalanceOperationModel(String operation) {
        this.operation = operation;
    }

    public String getOperation() { return operation; }

    public String getAccount() { return account; }
    public BalanceOperationModel withAccount(String value) {
        this.account = value;
        return this;
    }

    public Double getValue() { return value; }
    public BalanceOperationModel withValue(Double value) {
        this.value = value;
        return this;
    }

}
