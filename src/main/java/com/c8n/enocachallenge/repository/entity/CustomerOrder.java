package com.c8n.enocachallenge.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CustomerOrder {
    @Id
    @SequenceGenerator(name = "sq_order_id", sequenceName = "sq_order_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "sq_order_id")
    private long id;
    private Date createDate;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
