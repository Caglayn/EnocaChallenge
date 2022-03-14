package com.c8n.enocachallenge.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderRequestDto {
    private long id;
    private double totalPrice;
    private long customerId;
}
