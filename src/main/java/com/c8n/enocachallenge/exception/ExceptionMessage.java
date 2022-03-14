package com.c8n.enocachallenge.exception;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionMessage {
    /**
     * 404 -> Resource Not Found
     */
    private int code;
    private String message;
}
