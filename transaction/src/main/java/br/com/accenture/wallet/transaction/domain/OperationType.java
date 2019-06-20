package br.com.accenture.wallet.transaction.domain;

public interface OperationType {

    String TRANSFER = "transfer";

    String PAYOUT = "payout";

    String PAYMENT = "payment";

    String CREDIT = "credit";

    String DEBIT = "debit";

}
