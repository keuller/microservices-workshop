package br.com.accenture.wallet.customer.controller

import br.com.accenture.wallet.customer.service.ResourceNotFoundException
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CustomerErrorHandler {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFound(ex: ResourceNotFoundException): FailModel {
        return FailModel("fail", ex.message)
    }

    inner class FailModel(val type: String, val message: String?) {
        var code: Int = 0
    }

}
