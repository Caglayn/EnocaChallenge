package com.c8n.enocachallenge.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "sq_customer_id", sequenceName = "sq_customer_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "sq_customer_id")
    private long id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<CustomerOrder> customerOrders;
}
