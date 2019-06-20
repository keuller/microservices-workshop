package br.com.accenture.wallet.transaction.domain;

import br.com.accenture.wallet.transaction.domain.command.*;
import br.com.accenture.wallet.transaction.domain.event.*;
import br.com.accenture.wallet.transaction.domain.model.BalanceModel;
import br.com.accenture.wallet.transaction.domain.model.BalanceOperationModel;
import br.com.accenture.wallet.transaction.service.TransactionService;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;

@Aggregate
public class TransferAggregate {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @AggregateIdentifier
    private String transferId;

    private String balanceUrl;

    private transient TransactionService transactionService;

    private transient RestTemplate restClient;

    public TransferAggregate() {}

    @Autowired
    public void setTransactionService(TransactionService service) {
        this.transactionService = service;
    }

    @Autowired
    public void setRestClient(RestTemplate restTempl) { this.restClient = restTempl; }

    @CommandHandler
    public TransferAggregate(TransferRequestCommand cmd) {
        this.transferId = cmd.getTransferId();
        this.balanceUrl = cmd.getBalanceUrl();

        final TransferRequestedEvent event = new TransferRequestedEvent(cmd.getTransferId(), balanceUrl)
            .withSourceAccount(cmd.getSourceAccount())
            .withTargetAccount(cmd.getTargetAccount())
            .withAmount(cmd.getAmount());

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void withdrawnHandler(final WithdrawalCommand cmd) {
        final String url = balanceUrl.concat("/" + cmd.getAccount() + "/account");
        final BalanceModel sourceBalance = restClient.getForObject(url, BalanceModel.class);

        if (Objects.isNull(sourceBalance)) return;
        log.debug("Source account balance value is {}", sourceBalance.getValue());

        // check available balance in source account
        if (sourceBalance.getValue() < cmd.getValue()) {
            log.warn("The source account hasn't enough balance available.");
            throw new IllegalArgumentException("Balance unavailable for this transaction.");
        }

        final BalanceOperationModel operation = new BalanceOperationModel(OperationType.DEBIT)
            .withAccount(cmd.getAccount())
            .withValue(cmd.getValue());

        log.debug("Withdrawn from account {}", cmd.getAccount());
        final HttpEntity<BalanceOperationModel> data = new HttpEntity<>(operation, getHeaders());
        restClient.put(balanceUrl, data);

        transactionService.registerTransaction(operation);

        final WithdrawnEvent event = new WithdrawnEvent(cmd.getTransactionId())
            .withValue(cmd.getValue());
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void depositHandler(final DepositCommand cmd) {
        final BalanceOperationModel operation = new BalanceOperationModel(OperationType.CREDIT)
            .withAccount(cmd.getAccount())
            .withValue(cmd.getValue());

        log.debug("Depositing to account {}", cmd.getAccount());
        final HttpEntity<BalanceOperationModel> data = new HttpEntity<>(operation, getHeaders());
        restClient.put(balanceUrl, data);

        transactionService.registerTransaction(operation);

        AggregateLifecycle.apply(new DepositedEvent(cmd.getTransactionId()));
    }

    @CommandHandler
    public void transferCompleteHandler(final TransferCompleteCommand cmd) {
        AggregateLifecycle.apply(new TransferCompletedEvent(cmd.getTransferId()));
    }

    @CommandHandler
    public void cancelTransferHandler(TransferCancelCommand cmd) {
        AggregateLifecycle.apply(new TransferCancelledEvent(cmd.getTransferId(), cmd.getCause()));
    }

    @EventSourcingHandler
    public void onRequested(TransferRequestedEvent event) {
        this.transferId = event.getTransferId();
        this.balanceUrl = event.getBalanceUrl();
    }

    @EventSourcingHandler
    public void onCompleted(TransferCompletedEvent event) {
        // remove aggregate after completed flow
        log.debug("Transfer flow was completed.");
        AggregateLifecycle.markDeleted();
    }

    private HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
