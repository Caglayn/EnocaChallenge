package com.c8n.enocachallenge.controller;

import static com.c8n.enocachallenge.constants.RestApiUrls.*;

import com.c8n.enocachallenge.service.CustomerOrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(API+VERSION+ORDER)
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }
}
