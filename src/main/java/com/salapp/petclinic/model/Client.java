package com.salapp.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 * Client table model
 */



@Data
@Entity
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private final Long id = null;
    private String clientName;
    private String clientStatus;


    public Client() {
    }
}
