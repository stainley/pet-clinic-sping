package com.salapp.petclinic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Stainley Lebron
 * @since 2/24/20.
 */

@SpringBootTest
public class PetClinicApplicationIT {

    @Test
    public void sayHello(){
        Assertions.assertEquals("Hello", "Hello");
    }
}
