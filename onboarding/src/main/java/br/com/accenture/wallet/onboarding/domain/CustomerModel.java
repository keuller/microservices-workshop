package br.com.accenture.wallet.onboarding.domain;

import javax.validation.constraints.NotBlank;

public class CustomerModel {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    public CustomerModel() {
    }

    public String getId() {
        return id;
    }
    public CustomerModel setId(String value) {
        this.id = value;
        return this;
    }

    public String getName() {
        return name;
    }
    public CustomerModel setName(String nome) {
        this.name = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public CustomerModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
