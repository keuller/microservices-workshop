package br.com.accenture.wallet.onboarding.controller;

import br.com.accenture.wallet.onboarding.domain.CustomerModel;
import br.com.accenture.wallet.onboarding.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/onboarding")
public class OnboardingController {

    private OnboardingService service;

    @Autowired
    public OnboardingController(OnboardingService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid CustomerModel model) {
        service.create(model);
        return "confirmed";
    }

}
