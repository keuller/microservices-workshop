package br.com.accenture.wallet.onboarding.controller

import br.com.accenture.wallet.onboarding.domain.CustomerBean
import br.com.accenture.wallet.onboarding.service.OnboardingService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/onboarding")
class OnboardingController(val service: OnboardingService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid model: CustomerBean): String {
        return if (service.create(model))
            "confirmed"
        else
            "Onboarding process fail."
    }

}