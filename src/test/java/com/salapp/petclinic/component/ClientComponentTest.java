package com.salapp.petclinic.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        int value1 = 3;
        int value2 = 2;

        int total = clientComponent.sumTwoValue(value1, value2);
        Assertions.assertEquals(5, total);
    }
}
