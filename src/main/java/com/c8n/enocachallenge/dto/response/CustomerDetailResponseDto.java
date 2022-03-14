package com.c8n.enocachallenge.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetailResponseDto {
    private long id;
    private String name;
    private int age;
}
