package com.example.rabbitmq_setup;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Customer implements Serializable {
    @Id
    private Long id;
    private String email;
    private String firstname;
}
