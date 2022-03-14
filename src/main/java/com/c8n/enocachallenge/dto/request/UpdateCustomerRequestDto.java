package com.c8n.enocachallenge.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequestDto {
    private long id;
    private String name;
    private int age;
}
