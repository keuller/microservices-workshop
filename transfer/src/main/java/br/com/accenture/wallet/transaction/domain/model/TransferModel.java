package br.com.accenture.wallet.transaction.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransferModel {
    @NotBlank
    @JsonProperty("source")
    private String sourceAccount;

    @NotBlank
    @JsonProperty("target")
    private String targetAccount;

    @NotNull
    private Double amount;

    public String getSourceAccount() {
        return sourceAccount;
    }
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }
    public void setTargetAccount(String value) {
        this.targetAccount = value;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
