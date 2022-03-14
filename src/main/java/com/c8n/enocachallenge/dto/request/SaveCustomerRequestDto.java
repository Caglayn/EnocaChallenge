package com.c8n.enocachallenge.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveCustomerRequestDto {
    private String name;
    private int age;
}
