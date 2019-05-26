package br.com.accenture.wallet.customer

import br.com.accenture.wallet.customer.controller.CustomerController
import br.com.accenture.wallet.customer.domain.CustomerBean
import br.com.accenture.wallet.customer.service.CustomerService
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.junit.Before
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import javax.validation.Validation

class CustomerControllerTest {
    private val mockService = mock(CustomerService::class.java)

    lateinit var controller: CustomerController

    @Before
    fun setUp() {
        controller = CustomerController(mockService)
    }

    @Test
    fun testCreateCustomer() {
        val model = CustomerBean()
        model.name = "Abdoral Test"
        model.email = "adboral.test@test.com"
        model.gender = "male"

        `when`(mockService.create(model)).thenReturn(model)
        val result = controller.create(model)
        assertThat(result.id, notNullValue())
    }

    @Test
    fun testCreateCustomerWithValidation() {
        val validFactory = Validation.buildDefaultValidatorFactory()

        val model = CustomerBean()
        model.name = "Abdoral Test"

        val constraints = validFactory.validator.validate(model)

        assertThat(constraints.size, greaterThan(0))
    }

    @Test
    fun testUpdateCustomer() {
        val model = CustomerBean()
        model.name = "Abdoral Test"
        model.email = "adboral.test@test.com"
        model.gender = "male"

        `when`(mockService.update(model)).thenReturn(model)
        val result = controller.update("bcad-9173", model)
        assertThat(result?.id, notNullValue())
    }

}