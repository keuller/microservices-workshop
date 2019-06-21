package br.com.accenture.wallet.onboarding.controller;

import br.com.accenture.wallet.onboarding.domain.CustomerModel;
import br.com.accenture.wallet.onboarding.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;

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
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> create(@RequestBody @Valid CustomerModel model) {
        try {
            service.create(model);
            return ResponseEntity.created(new URI("/v1/onboarding")).body("Process completed.");
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
