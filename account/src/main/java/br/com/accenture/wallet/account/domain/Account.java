package br.com.accenture.wallet.account.domain;

import javax.persistence.*;
import java.util.*;

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
        this.id = id;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Account setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Account setType(Integer type) {
        this.type = type;
        return this;
    }

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
        if (!Account.class.equals(obj.getClass())) return false;
        final Account bean = (Account) obj;
        return bean.getId().equals(this.id);
    }

}
