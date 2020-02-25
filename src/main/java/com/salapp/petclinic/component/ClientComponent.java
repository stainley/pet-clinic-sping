package com.salapp.petclinic.component;

import org.springframework.stereotype.Component;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */
@Component
public class ClientComponent {


    public int sumTwoValue(int value1, int value2) {
        return value1 + value2;
    }
}
