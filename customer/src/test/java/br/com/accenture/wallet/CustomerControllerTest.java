package br.com.accenture.wallet;

import br.com.accenture.wallet.customer.controller.CustomerController;
import br.com.accenture.wallet.customer.domain.CustomerModel;
import br.com.accenture.wallet.customer.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomerControllerTest {

    private CustomerService mockService = mock(CustomerService.class);

    private CustomerController controller;

    @Before
    public void setUp() {
        controller = new CustomerController(mockService);
    }

    @Test
    public void testCreateCustomer() {
        CustomerModel model = new CustomerModel("", "")
            .setName("Abdoral Test")
            .setEmail("adboral.test@test.com")
            .setGender("male");

        when(mockService.create(model)).thenReturn(model.setId("abc123"));
        final CustomerModel modelResult = controller.create(model);
        assertThat(modelResult.getId(), notNullValue());
    }

    @Test
    public void testUpdateCustomer() {
        CustomerModel model = new CustomerModel("", "")
            .setName("Abdoral Test")
            .setEmail("adboral.test@test.com")
            .setGender("male");

        when(mockService.update(model)).thenReturn(model.setId("bcad-9173"));
        final CustomerModel modelResult = controller.update("bcad-9173", model);
        assertThat(modelResult.getId(), notNullValue());
    }

}
