package balance.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="balances")
public class Balance implements java.io.Serializable {
    public static final long serialVersionUID = 23764872348L;

    @Id
    @Column(name="balance_id")
    private String id;

    @Column(name="account_id")
    private String accountId;

    @Column(name="customer_id")
    private String customerId;

    private Double value;

    private String lastTrasaction;

    private Date lastUpdate;

    public Balance() {
        this.id = UUID.randomUUID().toString();
        this.lastUpdate = new Date();
    }

    public String getId() {
        return id;
    }

    public Balance setId(String id) {
        this.id = id;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public Balance setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Balance setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public Balance setValue(Double value) {
        this.value = value;
        return this;
    }

    public String getLastTrasaction() {
        return lastTrasaction;
    }

    public Balance setLastTrasaction(String lastTrasaction) {
        this.lastTrasaction = lastTrasaction;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Balance setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public boolean equals(Object obj) {
        if (Objects.isNull(obj)) return false;
        if (!Balance.class.equals(obj.getClass())) return false;
        final Balance bean = (Balance) obj;
        return bean.getId().equals(this.id);
    }

}
