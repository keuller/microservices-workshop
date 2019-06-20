package br.com.accenture.wallet.customer.domain;

import javax.persistence.*;
import java.util.*;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

@Entity
@Table(name="customers")
public class Customer implements java.io.Serializable {
    public static long serialVersionUID = 723839847562845L;

    @Id
    @Column(name="customer_id")
    private String id;
    private String name;
    private String email;
    private Integer gender;

    @Column(name="birth_day")
    private Date birthday;

    public Customer() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.email = "";
    }

    public String getId() {
        return id;
    }
    public Customer setId(String id) {
        if (nonNull(id) && !"".equals(id)) this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getGender() {
        return gender;
    }
    public Customer setGender(Integer gender) {
        if (nonNull(gender) && !"".equals(gender))
            this.gender = gender;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }
    public Customer setBirthday(Date birthday) {
        if (nonNull(birthday) && !"".equals(birthday))
            this.birthday = birthday;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (isNull(obj) || !obj.getClass().isAssignableFrom(Customer.class)) {
            return false;
        }
        final Customer bean = (Customer) obj;
        return bean.getId().equals(this.id);
    }

}
