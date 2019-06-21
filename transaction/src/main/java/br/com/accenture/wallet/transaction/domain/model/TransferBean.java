package br.com.accenture.wallet.transaction.domain.model;

public class TransferBean {
    private final String id;
    private String status;
    private String message;

    public TransferBean(String id) {
        this.id = id;
    }

    public String getId() { return id; }

    public String getStatus() { return status; }
    public TransferBean withStatus(String value) { this.status = value; return this; }

    public String getMessage() { return message; }
    public TransferBean withMessage(String msg) { this.message = msg; return this; }

}
