package com.salapp.petclinic.component;

import org.springframework.stereotype.Component;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */
@Component
public class ClientComponent {

    public int total(int suma, int sumb) {
        return suma + sumb;
    }
}
