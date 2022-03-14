package com.c8n.enocachallenge.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponseDto {
    private long id;
    private Date createDate;
    private double totalPrice;
}
