package br.com.accenture.wallet.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;
import java.text.*;
import java.util.Date;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerModel {
    private static volatile DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private String id;

    @NotBlank
    @Size(min=5)
    private String name;

    @NotBlank
    private String email;

    @Pattern(regexp = "male|female|other")
    private String gender;

    private String birthday;

    public CustomerModel() {
        this.name = "";
        this.email = "";
    }

    public CustomerModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public CustomerModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public CustomerModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public CustomerModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }
    public CustomerModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }
    public CustomerModel setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public static CustomerModel fromEntity(Customer bean) {
        final CustomerModel model = new CustomerModel();
        return model.setId(bean.getId())
            .setName(bean.getName())
            .setEmail(bean.getEmail())
            .setGender(toGender(bean.getGender()))
            .setBirthday(toBirthday(bean.getBirthday()));
    }

    public Customer toEntity() {
        final Customer customer = new Customer()
            .setId(id)
            .setName(name)
            .setEmail(email)
            .setBirthday(toBirthday(birthday))
            .setGender(toGender(gender));
        return customer;
    }

    private static String toGender(Integer value) {
        if (isNull(value)) return null;
        switch(value) {
            case 1: return "male";
            case 2: return "female";
            default: return "other";
        }
    }

    private Integer toGender(String value) {
        if (isNull(value)) return null;
        switch (value) {
            case "male":
                return 1;
            case "female":
                return 2;
            case "other":
                return 3;
            default:
                return null;
        }
    }

    private Date toBirthday(String value) {
        if (isNull(value)) return null;
        try {
            return df.parse(value);
        } catch (ParseException ex) {
            return null;
        }
    }

    private static String toBirthday(Date value) {
        if (isNull(value)) return null;
        return df.format(value);
    }
}
