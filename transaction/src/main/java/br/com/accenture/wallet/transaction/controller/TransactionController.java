package br.com.accenture.wallet.transaction.controller;

import br.com.accenture.wallet.transaction.domain.model.TransferBean;
import br.com.accenture.wallet.transaction.domain.command.TransferRequestCommand;
import br.com.accenture.wallet.transaction.domain.model.TransactionModel;
import br.com.accenture.wallet.transaction.domain.model.TransferModel;
import br.com.accenture.wallet.transaction.service.TransactionService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/v1/transactions", produces = APPLICATION_JSON_VALUE)
public class TransactionController {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${balance.endpoint}")
    private String balanceUrl;

    private final CommandGateway commandGateway;

    private final TransactionService service;

    @Autowired
    public TransactionController(CommandGateway cmdGateway, TransactionService transService) {
        this.commandGateway = cmdGateway;
        this.service = transService;
    }

    @PostMapping(value="/transfer")
    public CompletableFuture<TransferBean> create(@Valid @RequestBody TransferModel model) {
        final String transferId = UUID.randomUUID().toString();

        log.debug("Sending command to start tranfer flow with ID {}", transferId);

        return commandGateway.send(new TransferRequestCommand(transferId, balanceUrl, model))
            .thenApply(value -> new TransferBean((String) value)
                .withStatus("OK")
                .withMessage("Transfer done."))
            .exceptionally(err -> new TransferBean(transferId)
                .withStatus("Error")
                .withMessage(err.getMessage())) ;
    }

    @GetMapping("/{account}/extract")
    public List<TransactionModel> extract(@PathVariable String account) {
        return service.findTransactions(account);
    }

}
