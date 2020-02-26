package com.salapp.petclinic.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */
@SpringBootTest
public class ClientTest {

    @MockBean
    Client client;


    @Test
    public void clientModel() {
        client.setClientName("Stainley");
        client.setClientStatus("Active");

        Assertions.assertEquals("Stainley", client.getClientName());
    }
}
