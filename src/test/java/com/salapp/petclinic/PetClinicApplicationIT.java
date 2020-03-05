package com.salapp.petclinic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Stainley Lebron
 * @since 2/24/20.
 */

@SpringBootTest
@EnableCaching
public class PetClinicApplicationIT {

    @Autowired
    PetClinicApplication petClinicApplication;

    @Test
    public void sayHello(){
        Assertions.assertNotNull(petClinicApplication);
    }
}
