package br.com.accenture.wallet.account.domain;

import javax.persistence.*;
import java.util.*;

import static java.util.Objects.nonNull;

@Entity
@Table(name="accounts")
public class Account implements java.io.Serializable {
    public static long serialVersionUID = 983759284795824L;

    @Id
    @Column(name="account_id")
    private String id;

    @Column(name="customer_id")
    private String customerId;

    private Integer type;

    private boolean active;

    private Double transactionLimit;

    private Double balance;

    private Date createdAt;

    public Account() {
        this.id = UUID.randomUUID().toString();
        this.active = false;
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }
    public Account setId(String id) {
        if (nonNull(id) && !id.isBlank()) this.id = id;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }
    public Account setCustomerId(String value) {
        if (nonNull(value) && !value.isBlank()) this.customerId = value;
        return this;
    }

    public Integer getType() {
        return type;
    }
    public Account setType(Integer type) {
        if (nonNull(type)) this.type = type;
        return this;
    }

    public Double getTransactionLimit() { return transactionLimit; }
    public Account setTransactionLimit(Double value) { this.transactionLimit = value; return this; }

    public Double getBalance() { return balance; }
    public Account setBalance(Double value) { this.balance = value; return this; }

    public boolean isActive() {
        return active;
    }
    public Account setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public Account setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public boolean equals(Object obj) {
        if (Objects.isNull(obj)) return false;
        if (!obj.getClass().isAssignableFrom(Account.class)) return false;
        final Account bean = (Account) obj;
        return bean.getId().equals(this.id);
    }

}
