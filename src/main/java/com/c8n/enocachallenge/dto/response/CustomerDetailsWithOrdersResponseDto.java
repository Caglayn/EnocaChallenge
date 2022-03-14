package com.c8n.enocachallenge.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetailsWithOrdersResponseDto {
    private long id;
    private String name;
    private int age;
    private List<OrderDetailResponseDto> orders;
}
