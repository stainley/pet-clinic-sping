package com.salapp.petclinic.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */
@SpringBootTest
public class ClientComponentTest {

    @Autowired
    ClientComponent clientComponent;

    @Test
    public void totalTest() {
        int total = clientComponent.total(3, 2);
        Assertions.assertEquals(5, total);
    }
}
