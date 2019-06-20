package br.com.accenture.wallet.transaction.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

@Entity
@Table(name="transactions", schema = "transaction")
public class Transaction implements java.io.Serializable {
    public static long serialVersionUID = 723839847562845L;

    @Id
    @Column(name="transaction_id", nullable=false)
    private String id;

    @Column(name="account_id", nullable=false)
    private String account;

    @Column(nullable=false)
    private Integer type;

    @Column(nullable=false)
    private Integer operation;

    @Column(precision=12, scale=3)
    private BigDecimal amount;

    @Column(name="created_at", nullable=false)
    private Date created;

    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.amount = new BigDecimal(0.0);
        this.created = new Date();
    }

    public String getId() {
        return id;
    }
    public Transaction setId(String id) {
        if (nonNull(id) && !"".equals(id)) this.id = id;
        return this;
    }

    public String getAccount() { return account; }
    public Transaction setAccount(String value) {
        if (nonNull(value) && !"".equals(value)) this.account = value;
        return this;
    }

    public Integer getOperation() { return operation; }
    public Transaction setOperation(Integer value) {
        if (nonNull(value)) this.operation = value;
        return this;
    }

    public Integer getType() { return type; }
    public Transaction setType(Integer value) {
        if (nonNull(value)) this.type = value;
        return this;
    }

    public BigDecimal getAmount() { return amount; }
    public Transaction setAmount(BigDecimal value) {
        if (nonNull(value)) this.amount = value;
        return this;
    }

    public Date getCreated() {
        return created;
    }
    public Transaction setCreated(Date value) { return this; }

    @Override
    public boolean equals(Object obj) {
        if (isNull(obj) || !obj.getClass().isAssignableFrom(Transaction.class)) {
            return false;
        }
        final Transaction bean = (Transaction) obj;
        return bean.getId().equals(this.id);
    }

}
