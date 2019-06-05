package br.com.accenture.wallet.customer.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(schema="customer", name="customers")
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
        this.id = id;
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
        this.gender = gender;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Customer setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(obj)) return false;
        boolean isSameClass = (obj instanceof Customer);
        if (!isSameClass) return false;
        final Customer bean = (Customer) obj;
        return bean.getId().equals(this.id);
    }

}
